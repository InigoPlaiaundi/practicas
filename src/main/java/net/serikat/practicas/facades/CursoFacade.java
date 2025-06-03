package net.serikat.practicas.facades;

import java.util.List;

import net.serikat.practicas.entities.Curso;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Instalacion;
import net.serikat.practicas.entities.UsuarioInscripcion;

public interface CursoFacade {
	List<Curso> obtenerCursos();

	int contadorCurso();

	Curso getCursoById(int id);

	void insertCurso(Curso curso);

	void updateCurso(Curso curso);

	void deleteCurso(int idCurso);

	Curso getLastCurso();

}
