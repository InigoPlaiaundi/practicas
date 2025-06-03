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

import net.serikat.practicas.daos.rowmappers.InscripcionRowMapper;
import net.serikat.practicas.daos.rowmappers.UsuarioInscripcionRowMapper;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.UsuarioInscripcion;

@Repository
public class InscripcionDaoImpl implements InscripcionDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String SQL_SELECT_INSCRIPCIONES = "SELECT idInscripcion,idInstalacion,idCurso,horario_inicio,horario_fin,idMonitor FROM inscripcion ";
	private static final String SQL_SELECT_INSCRIPCION_BY_ID = SQL_SELECT_INSCRIPCIONES + " WHERE idInscripcion = ?";
	private static final String SQL_INSERT_INSCRIPCION = "INSERT INTO Inscripcion (idInstalacion, idCurso, horario_inicio, horario_fin, idMonitor) values (:idInstalacion, :idCurso, :horario_inicio, :horario_fin, :idMonitor)";
	private static final String SQL_DELETE_INSCRIPCION = "DELETE FROM INSCRIPCION WHERE idInscripcion = :idInscripcion";
	
	private static final String SQL_SELECT_USUARIO_INSCRIPCIONES = "SELECT idUsuario,idInscripcion FROM USUARIO_INSCRIPCION ";
	private static final String SQL_DELETE_USUARIO_INSCRIPCIONES = "DELETE FROM USUARIO_INSCRIPCION WHERE idInscripcion = :idInscripcion";

	
	public InscripcionDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<UsuarioInscripcion> getUsuarioInscripcion(int idUsuario) {

		String sql = "SELECT idUsuario, idInscripcion FROM USUARIO_INSCRIPCION WHERE idUsuario = :idUsuario";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idUsuario", idUsuario);

		RowMapper<UsuarioInscripcion> usuarioInscripcionRowMapper = new UsuarioInscripcionRowMapper();

		return this.namedParameterJdbcTemplate.query(sql, parameters, usuarioInscripcionRowMapper);

	}

	@Override
	public Inscripcion getInscripcionById(int idInscripcion) {
		return jdbcTemplate.queryForObject(SQL_SELECT_INSCRIPCION_BY_ID, new InscripcionRowMapper(), idInscripcion);
		
	}

	@Override
	public List<Inscripcion> obtenerInscripciones() {
		// TODO Auto-generated method stub
		// SQL_SELECT_USUARIO_INSCRIPCIONES

		RowMapper<Inscripcion> inscripcionRowMapper = new InscripcionRowMapper();
		List<Inscripcion> listaInscripciones = (List<Inscripcion>) this.jdbcTemplate.query(SQL_SELECT_INSCRIPCIONES,inscripcionRowMapper);

		return listaInscripciones;

	}

	@Override
	public List<UsuarioInscripcion>  obtenerUsuarioInscripciones() {
		// TODO Auto-generated method stub
		RowMapper<UsuarioInscripcion> usuarioInscripcion = new UsuarioInscripcionRowMapper();
		List<UsuarioInscripcion> listaUsuarioInscripciones = (List<UsuarioInscripcion>) this.jdbcTemplate.query(SQL_SELECT_USUARIO_INSCRIPCIONES,usuarioInscripcion);
		return listaUsuarioInscripciones;
	}

	@Override
	public List<Inscripcion> filtrarCursos(
	        Inscripcion inscripcion,
	        List<Inscripcion> listaInscripciones,
	        int idUsuario,
	        List<UsuarioInscripcion> listaUsuarioInscripciones) {

	    int idCursoBase = inscripcion.getIdCurso();
	    int idInstalacionBase = inscripcion.getIdInstalacion();

	    List<Inscripcion> alternativas = new ArrayList<>();

	    // Filtrar las inscripciones ya asociadas al usuario
	    for (Inscripcion i : listaInscripciones) {
	        boolean mismoCurso = i.getIdCurso() == idCursoBase; // Verifica que el curso sea el mismo
	        boolean otraInstalacion = i.getIdInstalacion() != idInstalacionBase; // Verifica que la instalación sea diferente
	        boolean yaInscrito = false;

	        // Verificamos si el usuario ya está inscrito en esta inscripción
	        for (UsuarioInscripcion ui : listaUsuarioInscripciones) {
	            if (ui.getIdUsuario() == idUsuario && ui.getIdInscripcion() == i.getIdInscripcion()) {
	                yaInscrito = true;
	                break;
	            }
	        }

	        // Si el curso es el mismo, la instalación es diferente y el usuario no está inscrito, lo agregamos como alternativa
	        if (mismoCurso && otraInstalacion && !yaInscrito) {
	            alternativas.add(i);
	        }
	    }

	    return alternativas;
	}

	@Override
	public void insertInscripcion(Inscripcion inscripcion) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(inscripcion);
		this.namedParameterJdbcTemplate.update(SQL_INSERT_INSCRIPCION, parameterSource);
	}

	@Override
	public void deleteInscripcion(int idInscripcion) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new MapSqlParameterSource("idInscripcion", idInscripcion);
		this.namedParameterJdbcTemplate.update(SQL_DELETE_INSCRIPCION, parameterSource);
	}
	
	@Override
	public void deleteUsuarioInscripciones(int idInscripcion) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("idInscripcion", idInscripcion);

		this.namedParameterJdbcTemplate.update(SQL_DELETE_USUARIO_INSCRIPCIONES, parameterSource);
	}
	


}
