package net.serikat.practicas.facades;

import java.util.List;

import net.serikat.practicas.entities.Instalacion;
import net.serikat.practicas.entities.Usuario;

public interface InstalacionFacade {
	List<Instalacion> obtenerInstalaciones();

	int contadorInstalacion();

	Instalacion getInstalacionByid(int id);

	void insertInstalacion(Instalacion instalacion);

	void updateInstalacion(Instalacion instalacion);

	void deleteInstalacion(int idInstalacion);

	Instalacion getLastInstalacion();
}
