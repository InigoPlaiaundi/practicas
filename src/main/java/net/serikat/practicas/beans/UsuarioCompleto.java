package net.serikat.practicas.beans;

import java.util.List;

import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Usuario;

public class UsuarioCompleto {

	private Usuario usuario;
	private List<Inscripcion> listaInscripciones;

	public UsuarioCompleto(Object object, List<Inscripcion> of) {
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioCompleto() {
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Inscripcion> getListaInscripciones() {
		return listaInscripciones;
	}

	public void setListaInscripciones(List<Inscripcion> listaInscripciones) {
		this.listaInscripciones = listaInscripciones;
	}

}
