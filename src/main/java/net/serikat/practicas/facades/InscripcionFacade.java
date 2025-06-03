package net.serikat.practicas.facades;

import java.util.List;

import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.UsuarioInscripcion;

public interface InscripcionFacade {
	
	public List<UsuarioInscripcion> getUsuarioInscripcion(int idUsuario);

	Inscripcion getInscripcionById(int idInscripcion);

	List<Inscripcion> obtenerInscripciones();

	/**
	 * 
	 * @return
	 */
	List<UsuarioInscripcion> obtenerUsuarioInscripciones();

	/**
	 * Devuelve los cursos en base a los filtros.
	 * @param inscripcion
	 * @param listaInscripciones
	 * @param idUsuario
	 * @param listaUsuarioInscripciones
	 * @return
	 */
	List<Inscripcion> filtrarCursos(Inscripcion inscripcion, List<Inscripcion> listaInscripciones, int idUsuario,
			List<UsuarioInscripcion> listaUsuarioInscripciones);

	void insertInscripcion(Inscripcion inscripcion);

	void deleteInscripcion(int idInscripcion);

	void deleteUsuarioInscripciones(int inscripcionId);

	

}
