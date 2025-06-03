package net.serikat.practicas.entities;

import java.sql.Time;

public class Instalacion {
	private int idInstalacion;
	private String nombre;
	private String descripcion;
	private String responsable;
	private int capacidad;
	private int aforo;
	private Time horario_apertura;
	private Time horario_cierre;

	public int getIdInstalacion() {
		return idInstalacion;
	}

	public void setIdInstalacion(int idInstalacion) {
		this.idInstalacion = idInstalacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public Time getHorario_apertura() {
		return horario_apertura;
	}

	public void setHorario_apertura(Time horario_apertura) {
		this.horario_apertura = horario_apertura;
	}

	public Time getHorario_cierre() {
		return horario_cierre;
	}

	public void setHorario_cierre(Time horario_cierre) {
		this.horario_cierre = horario_cierre;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Instalacion [idInstalacion=");
		builder.append(idInstalacion);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", responsable=");
		builder.append(responsable);
		builder.append(", capacidad=");
		builder.append(capacidad);
		builder.append(", aforo=");
		builder.append(aforo);
		builder.append(", horario_apertura=");
		builder.append(horario_apertura);
		builder.append(", horario_cierre=");
		builder.append(horario_cierre);
		builder.append("]");
		return builder.toString();
	}

}
