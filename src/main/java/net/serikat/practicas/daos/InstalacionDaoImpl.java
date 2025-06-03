package net.serikat.practicas.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.serikat.practicas.daos.rowmappers.InstalacionRowMapper;
import net.serikat.practicas.daos.rowmappers.UsuarioRowMapper;
import net.serikat.practicas.entities.Instalacion;
import net.serikat.practicas.entities.Usuario;

@Repository
public class InstalacionDaoImpl implements InstalacionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String SQL_SELECT_INSTALACIONES = "SELECT idInstalacion, nombre ,descripcion ,responsable, capacidad, aforo, horario_apertura,horario_cierre FROM instalacion";
	private static final String SQL_INSERT_INSTALACION = "INSERT INTO INSTALACION (idInstalacion, nombre ,descripcion ,responsable, capacidad, aforo, horario_apertura,horario_cierre) values (:idInstalacion, :nombre ,:descripcion ,:responsable, :capacidad, :aforo, :horario_apertura,:horario_cierre)";
	private static final String SELECT_INSTALACION_BY_ID = SQL_SELECT_INSTALACIONES + " WHERE idInstalacion = ?";
	private static final String SQL_UPDATE_INSTALACION = "UPDATE INSTALACION set nombre = :nombre, descripcion = :descripcion , responsable = :responsable ,capacidad = :capacidad, aforo =:aforo, horario_apertura = :horario_apertura,horario_cierre = :horario_cierre WHERE idInstalacion = :idInstalacion";
	private static final String SQL_DELETE_INSTALACION = "DELETE FROM INSTALACION WHERE idInstalacion = :idInstalacion";

	@Override
	public List<Instalacion> obtenerInstalaciones() {
		// TODO Auto-generated method stub
		RowMapper<Instalacion> usuarioRowMapper = new InstalacionRowMapper();
		List<Instalacion> listaInstalaciones = (List<Instalacion>) this.jdbcTemplate.query(SQL_SELECT_INSTALACIONES,
				usuarioRowMapper);

		return listaInstalaciones;
	}

	@Override
	public int contadorInstalacion() {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM INSTALACION";
		final List<Integer> resultList = this.jdbcTemplate.queryForList(sql, Integer.class);
		return resultList.get(0);
	}

	@Override
	public Instalacion getInstalacionByid(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SELECT_INSTALACION_BY_ID, new InstalacionRowMapper(), id);
	}

	@Override
	public void insertInstalacion(Instalacion instalacion) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(instalacion);
		this.namedParameterJdbcTemplate.update(SQL_INSERT_INSTALACION, parameterSource);
	}

	@Override
	public void updateInstalacion(Instalacion instalacion) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(instalacion);
		this.namedParameterJdbcTemplate.update(SQL_UPDATE_INSTALACION, parameterSource);
	}

	@Override
	public void deleteInstalacion(int idInstalacion) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new MapSqlParameterSource("idInstalacion", idInstalacion);
		this.namedParameterJdbcTemplate.update(SQL_DELETE_INSTALACION, parameterSource);
	}

	@Override
	public Instalacion getLastInstalacion() {
		// TODO Auto-generated method stub
		return null;
	}

}
