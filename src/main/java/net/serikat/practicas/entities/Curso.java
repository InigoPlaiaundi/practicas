package net.serikat.practicas.entities;

public class Curso {
	private int idCurso;
	private String nombre;
	private String descripcion;
	private int capacidad;


	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
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

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Curso [idCurso=");
		builder.append(idCurso);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", capacidad=");
		builder.append(capacidad);
		builder.append("]");
		return builder.toString();
	}



	

}
