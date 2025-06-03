/**
 * 
 */
package net.serikat.practicas.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.serikat.practicas.beans.Filtro;
import net.serikat.practicas.daos.UsuarioDao;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Usuario;

/**
 * 
 */
@Service
public class UsuarioFacadeImpl implements UsuarioFacade {

	@Autowired
	private UsuarioDao usuarioDao;

	@Transactional()
	@Override
	public void insertUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.insertUsuario(usuario);
	}

	@Override
	public int contadorUsuarios() {
		// TODO Auto-generated method stub
		int numUsuarios = usuarioDao.contadorUsuarios();
		return numUsuarios;
	}

	@Override
	public Usuario getUserByid(int id) {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioDao.getUserByid(id);
		return usuario;
	}

	@Transactional()
	@Override
	public void deleteUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		usuarioDao.deleteUsuario(idUsuario);
	}

	@Transactional()
	@Override
	public void updateUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.updateUsuario(usuario);
	}

	@Override
	public Usuario getLasUser() {
		// TODO Auto-generated method stub
		Usuario lastUsuario = usuarioDao.getLasUser();
		return lastUsuario;
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> listaUsuarios = usuarioDao.obtenerUsuarios();
		return listaUsuarios;
	}
	
	@Override
	public List<Usuario> obtenerMonitores() {
		// TODO Auto-generated method stub
		List<Usuario> listaMonitores = usuarioDao.obtenerMonitores();
		return listaMonitores;
	}
	
	@Override
	public List<Inscripcion> obtenerMonitoresInscripciones(Inscripcion inscripcion) {
		// TODO Auto-generated method stub
		List<Inscripcion> inscripciones = usuarioDao.obtenerMonitoresInscripciones(inscripcion);
		return inscripciones;
	}

	@Override
	public List<Usuario> buscador(Filtro filtro) {
		// TODO Auto-generated method stub
		List<Usuario> usuarioFiltrados = usuarioDao.buscador(filtro);
		return usuarioFiltrados;
	}

	@Override
	public Usuario getUser(String user) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		usuario = usuarioDao.getUser(user);
		return usuario;
	}

	@Transactional()
	@Override
	public void darDeAltaUsuario(Usuario usuario, Inscripcion inscripcion) {
		// TODO Auto-generated method stub
		usuarioDao.darDeAltaUsuario(usuario, inscripcion);
	}

	@Transactional()
	@Override
	public void updateInscripcion(Inscripcion inscripcion) {
		// TODO Auto-generated method stub
		usuarioDao.updateInscripcion(inscripcion);
	}

	@Override
	public Usuario getUserByEmail(String email) {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioDao.getUserByEmail(email);
		return usuario;
	}

	@Transactional()
	@Override
	public void deleteUsuarioInscripcion(int idUsuario, int idInscripcion) {
		// TODO Auto-generated method stub
		usuarioDao.deleteUsuarioInscripcion(idUsuario, idInscripcion);
	}

	@Override
	public void deleteUsuarioInscripciones(int usuarioId) {
		// TODO Auto-generated method stub
		usuarioDao.deleteUsuarioInscripciones(usuarioId);
	}


}
