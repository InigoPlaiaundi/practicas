package net.serikat.practicas.daos;

import java.util.List;
import net.serikat.practicas.beans.Filtro;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Usuario;

/**
 * Interfaz para el acceso y manipulación de la entidad Usuario.
 */
public interface UsuarioDao {

	/**
	 * Inserta un nuevo usuario en la base de datos.
	 * @param usuario Objeto Usuario que se desea insertar.
	 */
	void insertUsuario(Usuario usuario);

	/**
	 * Devuelve el número total de usuarios registrados.
	 * @return Número total de usuarios.
	 */
	int contadorUsuarios();

	/**
	 * Recupera un usuario a partir de su identificador único.
	 * @param id Identificador del usuario.
	 * @return Usuario correspondiente al identificador proporcionado. <br>
	 * Si no se encuentra ningún usuario, devuelve null.
	 */
	Usuario getUserByid(int id);

	/**
	 * Recupera un usuario a partir de su correo electrónico.
	 * @param email Correo electrónico del usuario.
	 * @return Usuario correspondiente al correo electrónico proporcionado. <br>
	 * Si no se encuentra ningún usuario, devuelve null.
	 */
	Usuario getUserByEmail(String email);

	/**
	 * Obtiene el listado completo de usuarios.
	 * @return Lista de todos los usuarios. <br>
	 * Si no existen registros, se devuelve una lista vacía.
	 */
	List<Usuario> obtenerUsuarios();

	/**
	 * Elimina un usuario de la base de datos mediante su identificador.
	 * @param idUsuario Identificador del usuario que se desea eliminar.
	 */
	void deleteUsuario(int idUsuario);

	/**
	 * Actualiza la información de un usuario existente.
	 * @param usuario Objeto Usuario con los datos actualizados.
	 */
	void updateUsuario(Usuario usuario);

	/**
	 * Recupera el último usuario insertado en la base de datos.
	 * @return Último objeto Usuario registrado. <br>
	 * Si no existen registros, devuelve null.
	 */
	Usuario getLasUser();

	/**
	 * Realiza una búsqueda de usuarios basada en un filtro personalizado.
	 * @param filtro Objeto Filtro con los criterios de búsqueda.
	 * @return Lista de usuarios que cumplen con los criterios del filtro. <br>
	 * Si no hay coincidencias, devuelve una lista vacía.
	 */
	List<Usuario> buscador(Filtro filtro);

	/**
	 * Recupera un usuario mediante su nombre de usuario o identificador lógico.
	 * @param user Nombre de usuario.
	 * @return Objeto Usuario correspondiente. <br>
	 * Si no existe, devuelve null.
	 */
	Usuario getUser(String user);

	/**
	 * Registra un nuevo usuario y lo asocia con una inscripción específica.
	 * @param usuario Objeto Usuario a registrar.
	 * @param inscripcion Inscripción asociada al usuario.
	 */
	void darDeAltaUsuario(Usuario usuario, Inscripcion inscripcion);

	/**
	 * Actualiza los datos de una inscripción existente.
	 * @param inscripcion Objeto Inscripcion con los datos actualizados.
	 */
	void updateInscripcion(Inscripcion inscripcion);

	/**
	 * Elimina la asociación entre un usuario y una inscripción específica.
	 * @param idUsuario Identificador del usuario.
	 * @param idInscripcion Identificador de la inscripción.
	 */
	void deleteUsuarioInscripcion(int idUsuario, int idInscripcion);

	/**
	 * Recupera el listado de usuarios que tienen el rol de monitores.
	 * @return Lista de monitores registrados. <br>
	 * Si no existen, se devuelve una lista vacía.
	 */
	List<Usuario> obtenerMonitores();

	/**
	 * Obtiene las inscripciones asociadas a monitores para una inscripción determinada.
	 * @param inscripcion Objeto Inscripcion de referencia.
	 * @return Lista de inscripciones asociadas a monitores. <br>
	 * Si no existen, devuelve una lista vacía.
	 */
	List<Inscripcion> obtenerMonitoresInscripciones(Inscripcion inscripcion);

	/**
	 * Elimina todas las asociaciones de inscripciones vinculadas a un usuario específico.
	 * @param usuarioId Identificador del usuario.
	 */
	void deleteUsuarioInscripciones(int usuarioId);

}
