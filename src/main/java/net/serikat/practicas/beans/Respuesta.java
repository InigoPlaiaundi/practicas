package net.serikat.practicas.beans;

import java.util.List;

import net.serikat.practicas.entities.Usuario;
import net.serikat.practicas.entities.UsuarioInscripcion;

public class Respuesta {

	private boolean correcto;
	private Usuario usuario;
	private List<ErrorApi> listaErrores;
	private List<Usuario> listaUsuarios;

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ErrorApi> getListaErrores() {
		return listaErrores;
	}

	public void setListaErrores(List<ErrorApi> listaErrores) {
		this.listaErrores = listaErrores;
	}

}
