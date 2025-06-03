package net.serikat.practicas.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.serikat.practicas.daos.InscripcionDao;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.UsuarioInscripcion;

@Service
@Transactional
public class InscripcionFacadeImpl implements InscripcionFacade {

	@Autowired
	InscripcionDao inscripcionDao;
	
	@Override
	public List<UsuarioInscripcion> getUsuarioInscripcion(int idUsuario) {
		// TODO Auto-generated method stub
		List<UsuarioInscripcion> listaInscripciones = inscripcionDao.getUsuarioInscripcion(idUsuario);
		return listaInscripciones;
	}
	
	@Override
	public List<Inscripcion> obtenerInscripciones() {
		// TODO Auto-generated method stub
		
		List<Inscripcion> listaInscripciones = inscripcionDao.obtenerInscripciones();
		return listaInscripciones;
	}
	
	@Override
	public List<UsuarioInscripcion> obtenerUsuarioInscripciones() {
		// TODO Auto-generated method stub
		List<UsuarioInscripcion> listaUsuarioInscripciones = inscripcionDao.obtenerUsuarioInscripciones();
		return listaUsuarioInscripciones;
	}

	@Override
	public List<Inscripcion> filtrarCursos(
	        Inscripcion inscripcion,
	        List<Inscripcion> listaInscripciones,
	        int idUsuario,
	        List<UsuarioInscripcion> listaUsuarioInscripciones) {
		// TODO Auto-generated method stub
		List<Inscripcion> listaAlternativas = inscripcionDao.filtrarCursos( inscripcion, listaInscripciones, idUsuario, listaUsuarioInscripciones);
		return listaAlternativas;
	}

	@Override
	public Inscripcion getInscripcionById(int idInscripcion) {
		// TODO Auto-generated method stub
		Inscripcion inscripcion = inscripcionDao.getInscripcionById(idInscripcion);
		return inscripcion;
	}

	@Override
	public void insertInscripcion(Inscripcion inscripcion) {
		// TODO Auto-generated method stub
		inscripcionDao.insertInscripcion(inscripcion);
	}

	@Override
	public void deleteInscripcion(int idInscripcion) {
		// TODO Auto-generated method stub
		inscripcionDao.deleteInscripcion(idInscripcion);
		
	}
	
	@Override
	public void deleteUsuarioInscripciones(int inscripcionId) {
		// TODO Auto-generated method stub
		inscripcionDao.deleteUsuarioInscripciones(inscripcionId);
	}

}
