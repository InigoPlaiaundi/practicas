package net.serikat.practicas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import net.serikat.practicas.beans.*;
import net.serikat.practicas.daos.InscripcionDao;
import net.serikat.practicas.entities.*;
import net.serikat.practicas.facades.*;

/**
 * Controlador de inscripciones mediante apis
 */

@RestController
@RequestMapping("api/inscripciones")
public class InscripcionController {

    @Autowired
    private UsuarioFacade usuarioFacade;

    @Autowired
    private CursoFacade cursoFacade;
    
	@Autowired
	private InscripcionFacade inscripcionFacade;

    @Autowired
    private InstalacionFacade instalacionFacade;

    /**
     * Metodo para obtener un usuario mediante el id
     * @param idUsuario numero identificador del usuario
     * @return devuelve un objeto {@link Respuesta} <br>
     * indicando si es correcto o no y devolviendo el usuario en caso de que exista.
     */
    @GetMapping("{id}")
    public ResponseEntity<RespuestaInscripcion> getUserById(@PathVariable("id") int idUsuario) {
    	
        RespuestaInscripcion respuesta = new RespuestaInscripcion();
        UsuarioCompleto usuarioCompleto = new UsuarioCompleto();
        List<ErrorApi> listaErrores = new ArrayList<ErrorApi>();
        usuarioCompleto.setListaInscripciones(new ArrayList<>());
        
        Usuario usuario = usuarioFacade.getUserByid(idUsuario);
        List<UsuarioInscripcion> listaInscripciones = inscripcionFacade.getUsuarioInscripcion(idUsuario);
        

        for (UsuarioInscripcion usuarioInscripcion : listaInscripciones) {
            Inscripcion inscripcion = inscripcionFacade.getInscripcionById(usuarioInscripcion.getIdInscripcion());
            inscripcion.setCurso(cursoFacade.getCursoById(inscripcion.getIdCurso()));
            inscripcion.setInstalacion(instalacionFacade.getInstalacionByid(inscripcion.getIdInstalacion()));
            inscripcion.setMonitor(usuarioFacade.getUserByid(inscripcion.getIdMonitor()));
            usuarioCompleto.getListaInscripciones().add(inscripcion);
            usuarioCompleto.setUsuario(usuario);
        }
        
        if (listaErrores.isEmpty()) {
            usuarioCompleto.setUsuario(usuario);
            respuesta.setCorrecto(true);
            respuesta.setUsuarioCompleto(usuarioCompleto);
        } else {
            respuesta.setCorrecto(false);
        }


        return new ResponseEntity<>(respuesta, HttpStatus.OK);
        

    }
    
    /**
     * Metodo para obtener todas las inscripciones disponibles de la base de datos.
     * @return Devuelve un onjeto {@link Respuesta} indicando si la solicitud ha sido correcta <br>
     * Y en caso de ser correcta incluye la lista de inscripciones.
     */
	@GetMapping("all")
	public ResponseEntity<RespuestaInscripcion> getAllInscripciones() {
		
		RespuestaInscripcion respuesta = new RespuestaInscripcion();
		List<ErrorApi> errores = new ArrayList<ErrorApi>();
		UsuarioCompleto usuarioCompleto = new UsuarioCompleto();
		usuarioCompleto.setListaInscripciones(new ArrayList<>());
		

		List<Inscripcion> inscripciones = inscripcionFacade.obtenerInscripciones();
		
		for (Inscripcion inscripcion : inscripciones) {
			 inscripcion.setCurso(cursoFacade.getCursoById(inscripcion.getIdCurso()));
	            inscripcion.setInstalacion(instalacionFacade.getInstalacionByid(inscripcion.getIdInstalacion()));
	            inscripcion.setMonitor(usuarioFacade.getUserByid(inscripcion.getIdMonitor()));
	            usuarioCompleto.getListaInscripciones().add(inscripcion);
		}
		respuesta.setListaAlternativas(inscripciones);

		if (respuesta.getListaAlternativas() != null) {
			respuesta.setCorrecto(true);

		} else {
			
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(new ArrayList<ErrorApi>());
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	/**
	 * Metodo que en base a las cursos a los cursos a los que esta incrito el usuario, <br> ofrece alternativas a las que se puede inscribir
	 * @param id identificador del usuario
	 * @param idInscripcion identificador de la inscripcion
	 * @return devuelve una lista de alternativas en caso de que la busqueda encuentre resultados si no manda un mensaje de error
	 */
    @GetMapping("{idUsuario}/{idInscripcion}")
    public ResponseEntity<RespuestaInscripcion> getAlternativas(@PathVariable("idUsuario") int id,
            @PathVariable("idInscripcion") int idInscripcion) {
        
        RespuestaInscripcion respuesta = new RespuestaInscripcion();
        
        Usuario usuario = usuarioFacade.getUserByid(id);
        Inscripcion inscripcion = inscripcionFacade.getInscripcionById(idInscripcion);
        
        if (usuario == null || inscripcion == null) {
            respuesta.setCorrecto(false);
            respuesta.setListaErrores(List.of(new ErrorApi("GENERAL", "", "Usuario o inscripción no encontrada")));
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }

        List<UsuarioInscripcion> listaUsuarioInscripciones = inscripcionFacade.obtenerUsuarioInscripciones();
        List<Inscripcion> listaInscripciones = inscripcionFacade.obtenerInscripciones();

        List<Inscripcion> listaAlternativas = inscripcionFacade.filtrarCursos(inscripcion, listaInscripciones, idInscripcion, listaUsuarioInscripciones);

        for (Inscripcion alternativa : listaAlternativas) {
            alternativa.setCurso(cursoFacade.getCursoById(alternativa.getIdCurso()));
            alternativa.setInstalacion(instalacionFacade.getInstalacionByid(alternativa.getIdInstalacion()));
        }

        respuesta.setCorrecto(true);
        respuesta.setListaAlternativas(listaAlternativas);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }


    @PostMapping("insert")
    public ResponseEntity<RespuestaInscripcion> createInscripcionUsuario(@RequestBody Usuario_Inscripcion usuario_inscripcion) {
        RespuestaInscripcion respuesta = new RespuestaInscripcion();
        ValidacionInscripcion validacion = new ValidacionInscripcion();
        UsuarioCompleto usuarioCompleto = new UsuarioCompleto();
        
        Usuario usuario = usuarioFacade.getUserByid(usuario_inscripcion.getIdUsuario());
        Inscripcion inscripcion = inscripcionFacade.getInscripcionById(usuario_inscripcion.getIdInscripcion());
        List<UsuarioInscripcion> listaInscripciones = inscripcionFacade.obtenerUsuarioInscripciones();
        List<ErrorApi> listaErrores = validacion.validarDarDeAlta(usuario, inscripcion,listaInscripciones);
        

        if (listaErrores.isEmpty()) {
            usuarioFacade.darDeAltaUsuario(usuario, inscripcion);
            respuesta.setCorrecto(true);
            usuarioCompleto.setUsuario(usuario);
            respuesta.setUsuarioCompleto(usuarioCompleto);
        } else {
            respuesta.setCorrecto(false);
            respuesta.setListaErrores(listaErrores);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
    
    @PostMapping("insertInscripcion")
    public ResponseEntity<RespuestaInscripcion> createInscripcion(@RequestBody Inscripcion inscripcion) {
        RespuestaInscripcion respuesta = new RespuestaInscripcion();
        ValidacionInscripcion validacion = new ValidacionInscripcion();
        
        List<Inscripcion> listaInscripciones =  inscripcionFacade.obtenerInscripciones();
        List<ErrorApi> listaErrores = validacion.validarInsert(inscripcion,listaInscripciones);
        
        if (listaErrores.isEmpty()) {
            respuesta.setCorrecto(true);
        	inscripcion.setCurso(cursoFacade.getCursoById(inscripcion.getIdCurso()));
            inscripcion.setInstalacion(instalacionFacade.getInstalacionByid(inscripcion.getIdInstalacion()));
            inscripcionFacade.insertInscripcion(inscripcion);
        } else {
            respuesta.setCorrecto(false);
            respuesta.setListaErrores(listaErrores);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RespuestaInscripcion> updateInscripcion(@PathVariable("id") int inscripcionId,
                                                                   @RequestBody Inscripcion inscripcion) {
        RespuestaInscripcion respuesta = new RespuestaInscripcion();
        ValidacionInscripcion validacion = new ValidacionInscripcion();
        List<ErrorApi> listaErrores = new ArrayList<ErrorApi>();
        
        inscripcion.setIdInscripcion(inscripcionId);

        if (listaErrores.isEmpty()) {
        	respuesta.setCorrecto(true);
        	inscripcion.setCurso(cursoFacade.getCursoById(inscripcion.getIdCurso()));
            inscripcion.setInstalacion(instalacionFacade.getInstalacionByid(inscripcion.getIdInstalacion()));
        	usuarioFacade.updateInscripcion(inscripcion);
            
		} else {
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(listaErrores);
		}

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
	@GetMapping("allCursos")
	public ResponseEntity<RespuestaInscripcion> getAllCursos() {
		RespuestaInscripcion respuesta = new RespuestaInscripcion();
		ErrorApi errorApi = new ErrorApi();

		List<Curso> cursos = cursoFacade.obtenerCursos();
		respuesta.setListaCursos(cursos);

		if (respuesta.getListaCursos() != null) {
			respuesta.setCorrecto(true);

		} else {
			
			errorApi.setCampo("GENERAL");
			errorApi.setMensaje("algo ha salido mal al recoger los cursos");
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(new ArrayList<ErrorApi>());
			respuesta.getListaErrores().add(errorApi);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{idInscripcion}")
	public ResponseEntity<RespuestaInscripcion> deleteInscripcion(@PathVariable("idInscripcion") int idInscripcion,
			HttpServletRequest request) {

		List<ErrorApi> listaErrores = new ArrayList<>();

		RespuestaInscripcion respuesta = new RespuestaInscripcion();
		Validacion validacion = new Validacion();

		if ( idInscripcion <= 0) {
			listaErrores.add(new ErrorApi("GENERAL", "", "Introduce los id de busqueda"));
		}
			
		Inscripcion inscripcion = inscripcionFacade.getInscripcionById(idInscripcion);
		

		if (listaErrores.isEmpty()) {
			respuesta.setCorrecto(true);
			inscripcionFacade.deleteUsuarioInscripciones(inscripcion.getIdInscripcion());
			inscripcionFacade.deleteInscripcion(idInscripcion);

		} else {
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(listaErrores);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	@DeleteMapping("{cursoId}")
	public ResponseEntity<RespuestaInscripcion> deleteCurso(@PathVariable("cursoId") int cursoId) {
		
		RespuestaInscripcion respuesta = new RespuestaInscripcion();
		List<ErrorApi> listaErrores = new ArrayList<>();
		
		Curso curso = cursoFacade.getCursoById(cursoId);
		
		if (curso != null) {
			respuesta.setCorrecto(true);
			cursoFacade.deleteCurso(cursoId);

		}else {
			respuesta.setCorrecto(false);
			listaErrores.add(new ErrorApi("GENERAL", "", "No se ha podido eliminar el curso"));
			
		}
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
    
	
	@GetMapping("allInstalaciones")
	public ResponseEntity<RespuestaInscripcion> getAllInstalaciones() {
		
		RespuestaInscripcion respuesta = new RespuestaInscripcion();
		ErrorApi errorApi = new ErrorApi();

		List<Instalacion> instalaciones = instalacionFacade.obtenerInstalaciones();
		respuesta.setListaInstalaciones(instalaciones);

		if (respuesta.getListaInstalaciones() != null) {
			respuesta.setCorrecto(true);

		} else {
			
			errorApi.setCampo("GENERAL");
			errorApi.setMensaje("algo ha salido mal al recoger lqs instalaciones");
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(new ArrayList<ErrorApi>());
			respuesta.getListaErrores().add(errorApi);
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	
	
	@PostMapping("monitoresInscripciones")
	public ResponseEntity<RespuestaInscripcion> getAllMonitoresInscripciones(@RequestBody Inscripcion inscripcion) {
		RespuestaInscripcion respuesta = new RespuestaInscripcion();
		ErrorApi errorApi = new ErrorApi();

		List<Inscripcion> inscripciones = usuarioFacade.obtenerMonitoresInscripciones(inscripcion);

		for (Inscripcion inscripcionLista : inscripciones) {
			int idMonitor = inscripcionLista.getIdMonitor();
			inscripcionLista.setMonitor(usuarioFacade.getUserByid(idMonitor));

		}

		if (inscripciones != null && !inscripciones.isEmpty()) {
			respuesta.setCorrecto(true);
			respuesta.setListaAlternativas(inscripciones);
		} else {
			errorApi.setCampo("GENERAL");
			errorApi.setMensaje("Algo ha salido mal al recoger los usuarios");
			respuesta.setCorrecto(false);
			respuesta.setListaErrores(List.of(errorApi));
		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

   
}