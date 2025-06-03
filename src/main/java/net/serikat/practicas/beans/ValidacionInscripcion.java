package net.serikat.practicas.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.serikat.practicas.entities.Curso;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Usuario;
import net.serikat.practicas.entities.UsuarioInscripcion;
import net.serikat.practicas.facades.CursoFacade;

public class ValidacionInscripcion {

	public List<ErrorApi> validarDarDeAlta(Usuario usuario, Inscripcion inscripcion,
			List<UsuarioInscripcion> listaInscripciones) {

		List<ErrorApi> listaErrores = new ArrayList<ErrorApi>();
		boolean encontrado = false;

		if (usuario != null || inscripcion != null) {

			for (UsuarioInscripcion usuarioInscripcion : listaInscripciones) {
				if (inscripcion.getIdInscripcion() == usuarioInscripcion.getIdInscripcion()
						&& usuario.getIdUsuario() == usuarioInscripcion.getIdUsuario()) {
					encontrado = true;
				}
			}

			if (encontrado) {
				listaErrores.add(new ErrorApi("GENERAL", usuario + " ", "El usuario ya esta inscrito"));
			}
		} else {
			listaErrores.add(new ErrorApi("GENERAL", " ", "El usuario o la inscripcion no pueden ser nulos"));
		}

		return listaErrores;

	}

	public List<ErrorApi> validarCapacidad(Inscripcion inscripcion, Curso curso) {

		/*
		 * if () {
		 * 
		 * }
		 */
		return null;

	}

	public List<ErrorApi> validarInsert(Inscripcion inscripcion,List<Inscripcion> listaInscripciones) {
		// TODO Auto-generated method stub
		
		List<ErrorApi> listaErrores = new ArrayList<ErrorApi>();
		
		for (Inscripcion inscripcion2 : listaInscripciones) {
			if (inscripcion.equals(inscripcion2)) {
				listaErrores.add(new ErrorApi("GENERAL", inscripcion + " ", "La inscripcion ya esta existe"));
			}
		}
		return listaErrores;
	}

}
