package net.serikat.practicas.daos;

import java.util.List;

import net.serikat.practicas.entities.Instalacion;
/**
 * Interfaz para el acceso y manipulación de la entidad {@link Instalacion}
 */
public interface InstalacionDao {

	/**
	 * Obtiene el listado completo de instalaciones.
	 * @return Devuelve una lista con todas las instalaciones registradas. <br>
	 * Si no hay ninguna instalación, devuelve una lista vacía.
	 */
	List<Instalacion> obtenerInstalaciones();

	/**
	 * Cuenta el número total de instalaciones registradas.
	 * @return Devuelve el número total de instalaciones en la base de datos.
	 */
	int contadorInstalacion();

	/**
	 * Obtiene una instalación según su identificador.
	 * @param id Identificador único de la instalación.
	 * @return Devuelve la instalación que coincide con el ID proporcionado. <br>
	 * En caso de no encontrarla, devuelve null.
	 */
	Instalacion getInstalacionByid(int id);

	/**
	 * Inserta una nueva instalación en la base de datos.
	 * @param instalacion Objeto Instalacion que se desea guardar.
	 */
	void insertInstalacion(Instalacion instalacion);

	/**
	 * Actualiza los datos de una instalación existente.
	 * @param instalacion Objeto Instalacion con los nuevos datos que se desean actualizar.
	 */
	void updateInstalacion(Instalacion instalacion);

	/**
	 * Elimina una instalación de la base de datos según su identificador.
	 * @param idInstalacion Identificador de la instalación que se desea eliminar.
	 */
	void deleteInstalacion(int idInstalacion);

	/**
	 * Obtiene la última instalación insertada en la base de datos.
	 * @return Devuelve la última instalación registrada. <br>
	 * Si no hay instalaciones registradas, devuelve null.
	 */
	Instalacion getLastInstalacion();
}

