package net.serikat.practicas.beans;

import java.util.List;

import net.serikat.practicas.entities.Curso;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Instalacion;

public class RespuestaInscripcion {
	private boolean correcto;
	private UsuarioCompleto usuarioCompleto;
	private List<Inscripcion> listaAlternativas;
	private List<Curso> listaCursos;
	private List<Instalacion> listaInstalaciones;
	private List<ErrorApi> listaErrores;

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	public List<ErrorApi> getListaErrores() {
		return listaErrores;
	}

	public void setListaErrores(List<ErrorApi> listaErrores) {
		this.listaErrores = listaErrores;
	}

	public List<Inscripcion> getListaAlternativas() {
		return listaAlternativas;
	}

	public void setListaAlternativas(List<Inscripcion> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}

	public UsuarioCompleto getUsuarioCompleto() {
		return usuarioCompleto;
	}

	public void setUsuarioCompleto(UsuarioCompleto usuarioCompleto) {
		this.usuarioCompleto = usuarioCompleto;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<Instalacion> getListaInstalaciones() {
		return listaInstalaciones;
	}

	public void setListaInstalaciones(List<Instalacion> listaInstalaciones) {
		this.listaInstalaciones = listaInstalaciones;
	}
	
	
	
	

}
