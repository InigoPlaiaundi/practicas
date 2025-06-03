package net.serikat.practicas.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.serikat.practicas.beans.Filtro;
import net.serikat.practicas.daos.rowmappers.CursoRowMapper;
import net.serikat.practicas.daos.rowmappers.InscripcionRowMapper;
import net.serikat.practicas.daos.rowmappers.UsuarioRowMapper;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UsuarioDaoImpl.class);

	private static final String SQL_SELECT_USUARIOS = "SELECT idUsuario, nombre,apellido1,apellido2 , username, password, role, email FROM usuario ";
	private static final String SQL_INSERT_USUARIO = "INSERT INTO USUARIO (nombre,apellido1,apellido2 ,username ,password , role ,email) values (:nombre,:apellido1,:apellido2, :username, :password, :role, :email)";
	private static final String SQL_UPDATE_USUARIO = "UPDATE USUARIO set nombre = :nombre, apellido1 = :apellido1 , apellido2 = :apellido2 ,username = :username, password =:password,role = :role, email = :email WHERE idUsuario = :idUsuario";
	private static final String SQL_DELETE_USUARIO = "DELETE FROM USUARIO WHERE idUsuario = :idUsuario";
	
	private static final String SELECT_USUARIO_BY_ID = SQL_SELECT_USUARIOS + " WHERE idUsuario = ?";
	private static final String SELECT_USUARIO = SQL_SELECT_USUARIOS + " WHERE email = :email OR username = :username" ;
	private static final String SELECT_USUARIOS_MONITOR = SQL_SELECT_USUARIOS + " WHERE role = 'monitor'";
	
	private static final String SELECT_USUARIO_BY_USERNAME = "SELECT idUsuario, nombre,apellido1,apellido2 , username, password, role, email FROM usuario WHERE username = ?";
	private static final String SQL_SELECT_LAST_USUARIO = SQL_SELECT_USUARIOS + " WHERE idUsuario = (SELECT MAX(idUsuario) from usuario)";

	private static final String SQL_INSERT_USUARIO_INSCRIPCION = "INSERT INTO USUARIO_INSCRIPCION (idUsuario,idInscripcion) VALUES (:idUsuario,:idInscripcion)";
	private static final String SQL_UPDATE_INSCRIPCION = "UPDATE INSCRIPCION set idInstalacion = :idInstalacion, idCurso = :idCurso ,horario_inicio = :horario_inicio ,horario_fin = :horario_fin ,idMonitor = :idMonitor WHERE idInscripcion = :idInscripcion";
	private static final String SQL_DELETE_USUARIO_INSCRIPCION = "DELETE FROM USUARIO_INSCRIPCION WHERE idUsuario = :idUsuario AND idInscripcion = :idInscripcion";
	private static final String SQL_DELETE_USUARIO_INSCRIPCIONES = "DELETE FROM USUARIO_INSCRIPCION WHERE idUsuario = :idUsuario";
	
	private static final String SELECT_INSCRIPCIONES_MONITOR = "SELECT a.* FROM inscripcion a "
			+ "INNER JOIN usuario b ON a.idMonitor = b.idUsuario " + "WHERE a.idCurso = :idCurso "
			+ "AND a.idInstalacion = :idInstalacion " + "AND a.horario_inicio = :horarioInicio";


	@Override
	public void insertUsuario(Usuario usuario) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(usuario);
		this.namedParameterJdbcTemplate.update(SQL_INSERT_USUARIO, parameterSource);
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(usuario);
		this.namedParameterJdbcTemplate.update(SQL_UPDATE_USUARIO, parameterSource);
	}

	@Override
	public void deleteUsuario(int idUsuario) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("idUsuario", idUsuario);
		this.namedParameterJdbcTemplate.update(SQL_DELETE_USUARIO, parameterSource);
	}

	@Override
	public int contadorUsuarios() {
		String sql = "SELECT count(*) FROM USUARIO";
		final List<Integer> resultList = this.jdbcTemplate.queryForList(sql, Integer.class);
		return resultList.get(0);
	}

	@Override
	public Usuario getUserByid(int id) {

		return jdbcTemplate.queryForObject(SELECT_USUARIO_BY_ID, new UsuarioRowMapper(), id);
	}

	@Override
	public Usuario getUserByEmail(String email) {
		// TODO Auto-generated method stub
		MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("email", email);  
	    params.addValue("username", email);  
	    return namedParameterJdbcTemplate.queryForObject(SELECT_USUARIO, params, new UsuarioRowMapper());

	}

	@Override
	public Usuario getUser(String user) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SELECT_USUARIO_BY_USERNAME, new UsuarioRowMapper(), user);
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		// ParameterizedBeanPropertyRowMapper.newInstance(Familia.class);
		RowMapper<Usuario> usuarioRowMapper = new UsuarioRowMapper();
		List<Usuario> listaUsuarios = (List<Usuario>) this.jdbcTemplate.query(SQL_SELECT_USUARIOS, usuarioRowMapper);

		return listaUsuarios;
	}
	
	@Override
	public List<Usuario> obtenerMonitores() {
		// ParameterizedBeanPropertyRowMapper.newInstance(Familia.class);
		RowMapper<Usuario> usuarioRowMapper = new UsuarioRowMapper();
		List<Usuario> listaMonitores = (List<Usuario>) this.jdbcTemplate.query(SELECT_USUARIOS_MONITOR, usuarioRowMapper);

		return listaMonitores;
	}
	
	@Override
	public List<Inscripcion> obtenerMonitoresInscripciones(Inscripcion inscripcion) {
		RowMapper<Inscripcion> inscripcionRowMapper = new InscripcionRowMapper();

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idCurso", inscripcion.getIdCurso());
		parameters.addValue("idInstalacion", inscripcion.getIdInstalacion());
		parameters.addValue("horarioInicio", inscripcion.getHorario_inicio());

		return this.namedParameterJdbcTemplate.query(SELECT_INSCRIPCIONES_MONITOR, parameters, inscripcionRowMapper);
	}

	@Override
	public Usuario getLasUser() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SQL_SELECT_LAST_USUARIO, new UsuarioRowMapper());
	}

	@Override
	public List<Usuario> buscador(Filtro filtro) {

		final Integer positivo = 1;

		final StringBuilder sql = new StringBuilder();
		sql.append(SQL_SELECT_USUARIOS).append(" WHERE 1 = 1 ");

		Map<String, String> parametros = new HashMap<String, String>();

		if (filtro.getNombre() != null && !filtro.getNombre().trim().equals("")) {

			if (positivo.equals(filtro.getContiene())) {
				sql.append(" AND nombre LIKE :nombre");
				parametros.put("nombre", "%" + filtro.getNombre() + "%");
			} else {
				sql.append(" AND nombre = :nombre");
				parametros.put("nombre", filtro.getNombre());
			}
		}

		if (filtro.getApellido1() != null && !filtro.getApellido1().trim().equals("")) {
			if (positivo.equals(filtro.getContiene())) {
				sql.append(" AND apellido1 LIKE :apellido1");
				parametros.put("apellido1", "%" + filtro.getApellido1() + "%");
			} else {
				sql.append(" AND apellido1 = :apellido1");
				parametros.put("apellido1", filtro.getApellido1());
			}
		}

		if (filtro.getApellido2() != null && !filtro.getApellido2().trim().equals("")) {
			if (positivo.equals(filtro.getContiene())) {
				sql.append(" AND apellido2 LIKE :apellido2");
				parametros.put("apellido2", "%" + filtro.getApellido2() + "%");
			} else {
				sql.append(" AND apellido2 = :apellido2");
				parametros.put("apellido2", filtro.getApellido2());
			}
		}

		if (filtro.getUsername() != null && !filtro.getUsername().trim().equals("")) {
			sql.append(" AND username = :username ");
			parametros.put("username", filtro.getUsername());
		}

		if (filtro.getRole() != null && !filtro.getRole().trim().equals("")) {
			sql.append(" AND role = :role ");
			parametros.put("role", filtro.getRole());
		}

		if (filtro.getEmail() != null && !filtro.getEmail().trim().equals("")) {
			sql.append(" AND email = :email ");
			parametros.put("email", filtro.getEmail());
		}

		RowMapper<Usuario> usuarioRowMapper = new UsuarioRowMapper();
		logger.error(sql.toString());
		List<Usuario> listaUsuarios = this.namedParameterJdbcTemplate.query(sql.toString(), parametros,
				usuarioRowMapper);

		return listaUsuarios;
	}

	@Override
	public void darDeAltaUsuario(Usuario usuario, Inscripcion inscripcion) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("idUsuario", usuario.getIdUsuario());
		parameters.put("idInscripcion", inscripcion.getIdInscripcion());

		this.namedParameterJdbcTemplate.update(SQL_INSERT_USUARIO_INSCRIPCION, parameters);
	}

	@Override
	public void updateInscripcion(Inscripcion inscripcion) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("idInscripcion", inscripcion.getIdInscripcion());
		parameters.put("idInstalacion", inscripcion.getIdInstalacion());
		parameters.put("idCurso", inscripcion.getIdCurso());
		parameters.put("horario_inicio", inscripcion.getHorario_inicio());
		parameters.put("horario_fin", inscripcion.getHorario_fin());
		parameters.put("idMonitor", inscripcion.getIdMonitor());

		this.namedParameterJdbcTemplate.update(SQL_UPDATE_INSCRIPCION, parameters);
	}

	@Override
	public void deleteUsuarioInscripcion(int idUsuario, int idInscripcion) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new MapSqlParameterSource()
		        .addValue("idUsuario", idUsuario)
		        .addValue("idInscripcion", idInscripcion);
		    
		    this.namedParameterJdbcTemplate.update(SQL_DELETE_USUARIO_INSCRIPCION, parameterSource);
	}

	@Override
	public void deleteUsuarioInscripciones(int usuarioId) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("idUsuario", usuarioId);

		this.namedParameterJdbcTemplate.update(SQL_DELETE_USUARIO_INSCRIPCIONES, parameterSource);
	}





}