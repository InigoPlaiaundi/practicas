package net.serikat.practicas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import net.serikat.practicas.beans.ErrorApi;
import net.serikat.practicas.beans.Respuesta;
import net.serikat.practicas.beans.RespuestaInscripcion;
import net.serikat.practicas.beans.Validacion;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Usuario;
import net.serikat.practicas.entities.UsuarioInscripcion;
import net.serikat.practicas.facades.InscripcionFacade;
import net.serikat.practicas.facades.UsuarioFacade;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioFacade usuarioFacade;
	
	@Autowired
	private InscripcionFacade inscripcionFacade;
	
	@GetMapping("{id}")
	public ResponseEntity<Respuesta> getUserById(@PathVariable("id") int id) {

		Respuesta respuesta = new Respuesta();
		ErrorApi errorApi = new ErrorApi();

		Usuario usuario = usuarioFacade.getUserByid(id);

		if (usuario != null) {
			respuesta.setCorrecto(true);
			respuesta.setUsuario(usuario);
		} else {
			errorApi.setCampo("GENERAL");
			errorApi.setMensaje("no existe el usuario");
			errorApi.setValorOriginal(id + "");
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(new ArrayList<ErrorApi>());
			respuesta.getListaErrores().add(errorApi);

		}
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping("/obtenerUsuario/{id}")
	public ResponseEntity<Respuesta> getUserByEmail(@PathVariable("id") String id) {
		Respuesta respuesta = new Respuesta();
		ErrorApi errorApi = new ErrorApi();
		Validacion validacion = new Validacion();
		Usuario usuario = new Usuario();
		
		usuario.setUsername(id);
		usuario.setEmail(id);
		
		List<Usuario> usuarios = usuarioFacade.obtenerUsuarios();
		List<ErrorApi> errores =  validacion.validarGetUsuario(usuario,usuarios);
		

		if (errores.isEmpty()) {
			respuesta.setCorrecto(true);
			Usuario usuarioValidado = usuarioFacade.getUserByEmail(id);
			respuesta.setUsuario(usuarioValidado);
		} else {
			errorApi.setCampo("GENERAL");
			errorApi.setMensaje("no existe el usuario");
			errorApi.setValorOriginal(id);
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(new ArrayList<ErrorApi>());
			respuesta.getListaErrores().add(errorApi);

		}
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	@GetMapping("all")
	public ResponseEntity<Respuesta> getAllUsers() {
		Respuesta respuesta = new Respuesta();
		ErrorApi errorApi = new ErrorApi();

		List<Usuario> usuarios = usuarioFacade.obtenerUsuarios();
		respuesta.setListaUsuarios(usuarios);

		if (respuesta.getListaUsuarios() != null) {
			respuesta.setCorrecto(true);

		} else {
			
			errorApi.setCampo("GENERAL");
			errorApi.setMensaje("algo ha salido mal al recoger los usuarios");
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(new ArrayList<ErrorApi>());
			respuesta.getListaErrores().add(errorApi);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping("allMonitores")
	public ResponseEntity<Respuesta> getAllMonitores() {
		Respuesta respuesta = new Respuesta();
		ErrorApi errorApi = new ErrorApi();

		List<Usuario> usuarios = usuarioFacade.obtenerMonitores();
		respuesta.setListaUsuarios(usuarios);

		if (respuesta.getListaUsuarios() != null) {
			respuesta.setCorrecto(true);

		} else {
			
			errorApi.setCampo("GENERAL");
			errorApi.setMensaje("algo ha salido mal al recoger los usuarios");
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(new ArrayList<ErrorApi>());
			respuesta.getListaErrores().add(errorApi);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Respuesta> deleteUser(@PathVariable("id") int usuarioId, HttpServletRequest request) {
		Respuesta respuesta = new Respuesta();
		Validacion validacion = new Validacion();

		String user = request.getHeader("user");
		Usuario usuario = usuarioFacade.getUser(user);
		Usuario usuario2 = usuarioFacade.getUserByid(usuarioId);
		
		List<Inscripcion> listaInscripciones = inscripcionFacade.obtenerInscripciones();
		List<ErrorApi> errores = validacion.validarDelete(usuario, usuario2, listaInscripciones);
		
		if (errores.isEmpty()) {
			respuesta.setCorrecto(true);
			respuesta.setUsuario(usuario);
			usuarioFacade.deleteUsuarioInscripciones(usuarioId);
			usuarioFacade.deleteUsuario(usuarioId);

		} else {
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(errores);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	/**
	 * 
	 * @param usuarioId el identificador del usuario que se va a modificar
	 * @param usuario
	 * @return
	 */
	@PutMapping("{id}")
	// http://localhost:8080/api/users/1
	public ResponseEntity<Respuesta> updateUser(@PathVariable("id") int usuarioId, @RequestBody Usuario usuario) {
		Respuesta respuesta = new Respuesta();
		Validacion validacion = new Validacion();

		usuario.setIdUsuario(usuarioId);

		List<Usuario> usuarios = usuarioFacade.obtenerUsuarios();
		List<ErrorApi> listaErrores = validacion.validarUsuario(usuario);
	//	validacion.validarInsert(usuario, listaErrores);
		validacion.validarUpdate(usuario, usuarios, listaErrores);

		if (listaErrores.isEmpty()) {
			respuesta.setCorrecto(true);
			usuario.setIdUsuario(usuarioId);
			usuarioFacade.updateUsuario(usuario);
			Usuario usuarioActualizado = usuarioFacade.getUserByid(usuarioId);
			respuesta.setUsuario(usuarioActualizado);
		} else {
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(listaErrores);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	@PostMapping("insert")
	public ResponseEntity<Respuesta> createUser(@RequestBody Usuario usuario) {

		Respuesta respuesta = new Respuesta();
		Validacion validacion = new Validacion();

		List<ErrorApi> listaErrores = validacion.validarUsuario(usuario);
		validacion.validarInsert(usuario, listaErrores);

		if (listaErrores.isEmpty()) {
			respuesta.setCorrecto(true);
			usuarioFacade.insertUsuario(usuario);
			Usuario usuarioRespuesta = usuarioFacade.getLasUser();
			respuesta.setUsuario(usuarioRespuesta);
		} else {
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(listaErrores);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/{idUsuario}/{idInscripcion}")
	public ResponseEntity<Respuesta> deleteUserInscripcion(@PathVariable("idUsuario") int idUsuario,
			@PathVariable("idInscripcion") int idInscripcion, HttpServletRequest request) {

		List<ErrorApi> listaErrores = new ArrayList<>();

		Respuesta respuesta = new Respuesta();
		Validacion validacion = new Validacion();

		if (idUsuario <= 0 || idInscripcion <= 0) {
			listaErrores.add(new ErrorApi("GENERAL", "", "Introduce los id de busqueda"));
		}

		Usuario usuario = usuarioFacade.getUserByid(idUsuario);
		

		if (listaErrores.isEmpty()) {
			respuesta.setCorrecto(true);
			respuesta.setUsuario(usuario);
			usuarioFacade.deleteUsuarioInscripcion(idUsuario, idInscripcion);
		} else {
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(listaErrores);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping("allUsuarioInscripciones/{idInscripcion}")
	public ResponseEntity<Respuesta> getAllUsuarioInscripciones(@PathVariable("idInscripcion") int idInscripcion) {
		
		Respuesta respuesta = new Respuesta();
		ErrorApi errorApi = new ErrorApi();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		List<UsuarioInscripcion> usuarioInscripciones = inscripcionFacade.obtenerUsuarioInscripciones();
		for (UsuarioInscripcion usuarioInscripcion : usuarioInscripciones) {
			if (usuarioInscripcion.getIdInscripcion() == idInscripcion) {
				Usuario usuario = usuarioFacade.getUserByid(usuarioInscripcion.getIdUsuario());
				listaUsuarios.add(usuario);
			}
		}
		
		respuesta.setListaUsuarios(listaUsuarios);
		
		if (respuesta.getListaUsuarios() != null && !respuesta.getListaUsuarios().isEmpty()) {
			respuesta.setCorrecto(true);
			

		} else {
			
			errorApi.setCampo("GENERAL");
			errorApi.setMensaje("algo ha salido mal al recoger lqs inscripciones");
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(new ArrayList<ErrorApi>());
			respuesta.getListaErrores().add(errorApi);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	



}
