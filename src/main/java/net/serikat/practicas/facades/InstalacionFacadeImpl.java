package net.serikat.practicas.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.serikat.practicas.daos.InstalacionDao;
import net.serikat.practicas.entities.Instalacion;
import net.serikat.practicas.entities.Usuario;

@Service
public class InstalacionFacadeImpl implements InstalacionFacade{
	
	@Autowired
	InstalacionDao instalacionDao;

	@Override
	public List<Instalacion> obtenerInstalaciones() {
		// TODO Auto-generated method stub
		List<Instalacion> instalaciones = instalacionDao.obtenerInstalaciones();
		return instalaciones;
	}

	@Override
	public int contadorInstalacion() {
		// TODO Auto-generated method stub
		int numInstalaciones = instalacionDao.contadorInstalacion();
		return numInstalaciones;
	}

	@Override
	public Instalacion getInstalacionByid(int id) {
		// TODO Auto-generated method stub
		Instalacion instalacion = instalacionDao.getInstalacionByid(id);
		return instalacion;
	}

	@Override
	public void insertInstalacion(Instalacion instalacion) {
		// TODO Auto-generated method stub
		instalacionDao.insertInstalacion(instalacion);
	}

	@Override
	public void updateInstalacion(Instalacion instalacion) {
		// TODO Auto-generated method stub
		instalacionDao.updateInstalacion(instalacion);
	}

	@Override
	public void deleteInstalacion(int idInstalacion) {
		// TODO Auto-generated method stub
		instalacionDao.deleteInstalacion(idInstalacion);;
	}

	@Override
	public Instalacion getLastInstalacion() {
		// TODO Auto-generated method stub
		return null;
	}

}
