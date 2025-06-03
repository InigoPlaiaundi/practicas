package net.serikat.practicas.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.serikat.practicas.daos.CursoDao;
import net.serikat.practicas.entities.Curso;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.UsuarioInscripcion;

@Service
@Transactional
public class CursoFacadeImpl implements CursoFacade {

	@Autowired
	CursoDao cursoDao;

	@Override
	public List<Curso> obtenerCursos() {
		// TODO Auto-generated method stub
		List<Curso> listaCursos = cursoDao.obtenerCursos();
		return listaCursos;
	}

	@Override
	public int contadorCurso() {
		// TODO Auto-generated method stub
		int numeroCursos = cursoDao.contadorCurso();
		return numeroCursos;
	}

	@Override
	public Curso getCursoById(int id) {
		// TODO Auto-generated method stub
		Curso curso = cursoDao.getCursoById(id);
		return curso;
	}

	@Override
	public void insertCurso(Curso curso) {
		// TODO Auto-generated method stub
		cursoDao.insertCurso(curso);
	}

	@Override
	public void updateCurso(Curso curso) {
		// TODO Auto-generated method stub
		cursoDao.updateCurso(curso);
	}

	@Override
	public void deleteCurso(int idCurso) {
		// TODO Auto-generated method stub
		cursoDao.deleteCurso(idCurso);
	}

	@Override
	public Curso getLastCurso() {
		// TODO Auto-generated method stub
		return null;
	}

}
