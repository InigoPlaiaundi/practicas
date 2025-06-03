package net.serikat.practicas.daos;

import java.util.List;

import net.serikat.practicas.entities.Curso;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.UsuarioInscripcion;

/**
 * Clase que representa la entidad {@link Curso} 
 */
public interface CursoDao {
	
	/**
	 * Obtiene el listado completo de cursos
	 * 
	 * @return Devuelve el listado de cursos disponibles. <br>
	 *         Si no hay ningun curso devuelve el listado vacio.
	 */
	List<Curso> obtenerCursos();
	
	/**
	 * Obtiene el numero de cursos existentes en la BBDD.
	 * 
	 * @return Devuelve el numero de cursos. <br>
	 *         En caso de que no haya cursos devuelve 0
	 */
	int contadorCurso();
	
	/**
	 * Obtiene un curso de la base de datos mediante su idCurso.
	 * 
	 * @param id identificador del curso
	 * @return devuelve el curso que coincidada con el id de busqueda.
	 */
	Curso getCursoById(int id);
	
	/**
	 * Inserta un nuevo curso en la base de datos.
	 * 
	 * @param curso el objeto que será insertado en la tabla
	 */
	void insertCurso(Curso curso);

	/**
	 * Actualiza un curso con los datos del nuevo curso que recibe.
	 * @param curso el objeto que será insertado en la tabla
	 */
	void updateCurso(Curso curso);

	/**
	 * Metodo para eliminar un curso mediante su id
	 * @param idCurso identificador del curso
	 */
	void deleteCurso(int idCurso);
	
	

	Curso getLastCurso();

}
