package net.serikat.practicas.beans;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Usuario;
import net.serikat.practicas.entities.UsuarioInscripcion;
import net.serikat.practicas.facades.CursoFacade;
import net.serikat.practicas.facades.UsuarioFacade;

public class Validacion {

	@Autowired
	private UsuarioFacade usuarioFacade;


	public List<ErrorApi> validarUsuario(Usuario filtro) {
		List<ErrorApi> errores = new ArrayList<>();

		final String nombre = filtro.getNombre();
		final String apellido1 = filtro.getApellido1();
		final String apellido2 = filtro.getApellido2();
		final String username = filtro.getUsername();
		final String role = filtro.getRole();
		final String email = filtro.getEmail();
		final Integer contiene = filtro.getContiene();

		// Validar si nombre o apellidos tiene mas de 3 caracteres
		if (nombre != null && nombre.length() < 3 && contiene == 0) {
			errores.add(new ErrorApi("nombre", filtro.getNombre().toString(),
					"El nombre tiene que tener 3 caracteres o mas"));
		}

		if (apellido1 != null && apellido1.length() < 3 && filtro.getContiene() == 0) {
			errores.add(new ErrorApi("apellido1", filtro.toString(),
					"El primer apellido tiene que tener 3 caracteres o mas"));
		}

		if (apellido2 != null && apellido2.length() < 3 && contiene == 0) {
			errores.add(new ErrorApi("apellido2", filtro.toString(),
					"El segundo apellido tiene que tener 3 caracteres o mas"));
		}

		// Validar si role existe
		boolean encontrado = false;
		if (filtro.getRole() != null) {
			for (RolesEnum roleEnum : RolesEnum.values()) {
				String roleUsuario = role.toUpperCase();
				if (roleUsuario.equals(roleEnum.toString())) {
					encontrado = true;
					break;
				}
			}
		}

		if (!encontrado && role != null) {
			errores.add(new ErrorApi("role", filtro.getRole().toString(), "El rol indicado no existe"));
		}

		return errores;
	}

	public void validarInsert(Usuario usuario, List<ErrorApi> listaErrores) {

		final String nombre = usuario.getNombre();
		final String apellido1 = usuario.getApellido1();
		final String apellido2 = usuario.getApellido2();
		final String username = usuario.getUsername();
		final String password = usuario.getPassword();
		final String role = usuario.getRole();

		if (nombre == null || nombre.trim().isEmpty()) {
			listaErrores.add(new ErrorApi("nombre", nombre, "El nombre no puede estar vacio"));
		}
		if (apellido1 == null || apellido1.trim().isEmpty()) {
			listaErrores.add(new ErrorApi("apellido1", apellido1, "El apellido1 no puede estar vacío"));
		}
		if (username == null || username.trim().isEmpty()) {
			listaErrores.add(new ErrorApi("username", username, "El username no puede estar vacio"));
		}
		if (password == null || password.trim().isEmpty()) {
			listaErrores.add(new ErrorApi("password", password, "El password no puede estar vacio"));
		}
		if (role == null || role.trim().isEmpty()) {
			listaErrores.add(new ErrorApi("role", role, "El role no puede estar vacio"));
		}

	/*	Usuario user = usuarioFacade.getUser(usuario.getUsername());
		if (username != null && username.equals(user.getUsername())) {
			listaErrores.add(new ErrorApi("username", "username = " + username, "Ese nombre de usuario ya existe"));
		}

		if (usuario.equals(user)) {
			listaErrores.add(new ErrorApi("GENERAL", usuario + "", "Ese  usuario ya existe"));
		}*/
	}

	public void validarUpdate(Usuario usuario, List<Usuario> usuariosFiltrados, List<ErrorApi> listaErrores) {
		// TODO Auto-generated method stub

		final int idUsuario = usuario.getIdUsuario();

		boolean encontrado = false;
		for (Usuario usuarioFiltrado : usuariosFiltrados) {
			if (idUsuario == usuarioFiltrado.getIdUsuario()) {
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			listaErrores.add(new ErrorApi("GENERAL", usuario + "", "El usuario que intentas actualizar no existe"));
		}

	}

	public List<ErrorApi> validarDelete(Usuario usuario, Usuario usuario2, List<Inscripcion> listaInscripciones) {
		
		List<ErrorApi> errores = new ArrayList<>();
		boolean encontrado = false;

		if (!usuario.getRole().equals("admin") ) {
			errores.add(new ErrorApi("GENERAL", "", "No tienes permisos"));
		}
		
		for (Inscripcion inscripcion : listaInscripciones) {
			if (inscripcion.getIdMonitor() == usuario2.getIdUsuario()) {
				encontrado = true;
			}
		}
		
		if (usuario2.getRole().equals("monitor") && encontrado) {
			errores.add(new ErrorApi("role",usuario2.getUsername() + "", "No puedes eliminar al usuario por que es un monitor"));
		}

		return errores;
	}

	public List<ErrorApi> validarGetUsuario(Usuario usuario, List<Usuario> usuarios) {
		// TODO Auto-generated method stub

		List<ErrorApi> errores = new ArrayList<>();
		boolean encontrado = false;

		for (Usuario usuario2 : usuarios) {
			if (usuario.getUsername().equals(usuario2.getUsername())
					|| usuario.getEmail().equals(usuario2.getEmail())) {
				encontrado = true;
				break;

			} else {
				encontrado = false;

			}
		}

		if (!encontrado) {
			errores.add(new ErrorApi("nombre", usuario.getUsername(), "Usuario no encontrado"));
		}

		return errores;
	}

}
