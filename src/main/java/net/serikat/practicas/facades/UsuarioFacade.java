package net.serikat.practicas.facades;

import java.util.List;

import net.serikat.practicas.beans.Filtro;
import net.serikat.practicas.beans.Respuesta;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Usuario;

public interface UsuarioFacade {
	void insertUsuario(Usuario usuario);

	int contadorUsuarios();

	Usuario getUserByid(int id);

	Usuario getUserByEmail(String email);

	List<Usuario> obtenerUsuarios();

	void deleteUsuario(int idUsuario);

	void updateUsuario(Usuario usuario);

	Usuario getLasUser();

	List<Usuario> buscador(Filtro filtro);

	Usuario getUser(String user);

	void darDeAltaUsuario(Usuario usuario, Inscripcion inscripcion);

	void updateInscripcion(Inscripcion inscripcion);

	void deleteUsuarioInscripcion(int idUsuario, int idInscripcion);

	List<Usuario> obtenerMonitores();

	List<Inscripcion> obtenerMonitoresInscripciones(Inscripcion inscripcion);

	void deleteUsuarioInscripciones(int usuarioId);
}
