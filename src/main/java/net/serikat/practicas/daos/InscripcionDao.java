package net.serikat.practicas.daos;

import java.util.List;

import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.UsuarioInscripcion;

/**
 * Interfaz para el acceso y manipulación de la entidad {@link Inscripcion}
 */
public interface InscripcionDao {

	/**
	 * Metodo para obtener una inscripcion mediante su id.
	 * @param idInscripcion
	 * @return devuelve la inscripcion que coincida con el parametro de busqueda. <br>
	 * En caso de no encontralo devuelve null.
	 */
	Inscripcion getInscripcionById(int idInscripcion);

	/**
	 * Obtiene el listado completo de cursos
	 * @return Devuelve el listado de cursos disponibles. <br>
	 * Si no hay ningun curso devuelve el listado vacio.
	 */
	List<Inscripcion> obtenerInscripciones();
	
	/**
	 * Obtiene el listado completo de la tabla UsuarioInscripcion
	 * @return Devuelve el listado de la relacion entre usuario e inscripciones. <br>
	 * Si no hay ningun resultado devuelve el listado vacio.*
	 */
	List<UsuarioInscripcion> obtenerUsuarioInscripciones();

	/**
	 * Metodo para devolver una lista de alternativas de inscripciones en base a las inscripciones a las que esta incrito el usuario
	 * 
	 * @param inscripcion
	 * @param listaInscripciones
	 * @param idUsuario
	 * @param listaUsuarioInscripciones
	 * @return devuelve una lista de alternativas de los cursos a los que el usuario esta inscrito <br>
	 * En caso de no haber altrnativas devuelve una lista vacia.
	 */
	List<Inscripcion> filtrarCursos(Inscripcion inscripcion, List<Inscripcion> listaInscripciones, int idUsuario,
	
	
	List<UsuarioInscripcion> listaUsuarioInscripciones);
	
	/**
	 * Metodo para crear e insertar una nueva inscripcion en la base de datos.
	 * @param inscripcion
	 */
	void insertInscripcion(Inscripcion inscripcion);
	
	/**
	 * elimina la inscripcion que coincida con el parametro idInscripcion
	 * @param idInscripcion
	 */
	void deleteInscripcion(int idInscripcion);
	
	/**
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<UsuarioInscripcion> getUsuarioInscripcion(int idUsuario);

	/**
	 * Metodo para eliminar un usuario en base al usuarioId
	 * @param usuarioId
	 */
	void deleteUsuarioInscripciones(int usuarioId);
}
