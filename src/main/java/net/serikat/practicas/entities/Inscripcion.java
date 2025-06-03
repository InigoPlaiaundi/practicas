package net.serikat.practicas.entities;

import java.sql.Time;

public class Inscripcion {
	private int idInscripcion;
	private int idInstalacion;
	private int idCurso;
	private Time horario_inicio;
	private Time horario_fin;
	private int idMonitor;
	private Curso curso;
	private Instalacion instalacion;
	private Usuario monitor;

	public int getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(int idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public int getIdInstalacion() {
		return idInstalacion;
	}

	public void setIdInstalacion(int idInstalacion) {
		this.idInstalacion = idInstalacion;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public Time getHorario_inicio() {
		return horario_inicio;
	}

	public void setHorario_inicio(Time horario_inicio) {
		this.horario_inicio = horario_inicio;
	}

	public Time getHorario_fin() {
		return horario_fin;
	}

	public void setHorario_fin(Time horario_fin) {
		this.horario_fin = horario_fin;
	}

	public int getIdMonitor() {
		return idMonitor;
	}

	public void setIdMonitor(int idMonitor) {
		this.idMonitor = idMonitor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public Usuario getMonitor() {
		return monitor;
	}

	public void setMonitor(Usuario monitor) {
		this.monitor = monitor;
	}
	
	

}
