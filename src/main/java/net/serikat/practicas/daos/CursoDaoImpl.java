package net.serikat.practicas.daos;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.serikat.practicas.daos.rowmappers.CursoRowMapper;
import net.serikat.practicas.daos.rowmappers.InscripcionRowMapper;
import net.serikat.practicas.daos.rowmappers.UsuarioInscripcionRowMapper;
import net.serikat.practicas.entities.Curso;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.UsuarioInscripcion;

@Repository
public class CursoDaoImpl implements CursoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String SQL_SELECT_CURSOS = "SELECT idCurso, nombre ,descripcion , capacidad FROM curso";
	private static final String SQL_INSERT_CURSO = "INSERT INTO CURSO ( idCurso, nombre ,descripcion , capacidad) values (:idCurso, :nombre ,:descripcion , :capacidad)";
	private static final String SELECT_CURSO_BY_ID = SQL_SELECT_CURSOS + " WHERE idCurso = ?";
	private static final String SQL_UPDATE_CURSO = "UPDATE CURSO set nombre = :nombre, descripcion = :descripcion ,  capacidad =:capacidad  WHERE idCurso = :idCurso";
	private static final String SQL_DELETE_CURSO = "DELETE FROM CURSO WHERE idCurso = :idCurso";

	@Override
	public List<Curso> obtenerCursos() {
		// TODO Auto-generated method stub
		RowMapper<Curso> cursoRowMapper = new CursoRowMapper();
		List<Curso> listaCursos = (List<Curso>) this.jdbcTemplate.query(SQL_SELECT_CURSOS, cursoRowMapper);

		return listaCursos;
	}

	@Override
	public int contadorCurso() {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM CURSO";
		final List<Integer> resultList = this.jdbcTemplate.queryForList(sql, Integer.class);
		return resultList.get(0);
	}

	@Override
	public Curso getCursoById(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SELECT_CURSO_BY_ID, new CursoRowMapper(), id);
	}

	@Override
	public void insertCurso(Curso curso) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(curso);
		this.namedParameterJdbcTemplate.update(SQL_INSERT_CURSO, parameterSource);
	}

	@Override
	public void updateCurso(Curso curso) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(curso);
		this.namedParameterJdbcTemplate.update(SQL_UPDATE_CURSO, parameterSource);
	}

	@Override
	public void deleteCurso(int idCurso) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new MapSqlParameterSource("idCurso", idCurso);
		this.namedParameterJdbcTemplate.update(SQL_DELETE_CURSO, parameterSource);
	}

	@Override
	public Curso getLastCurso() {
		// TODO Auto-generated method stub
		return null;
	}

}
