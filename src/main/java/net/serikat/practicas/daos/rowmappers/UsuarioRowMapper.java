package net.serikat.practicas.daos.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.serikat.practicas.entities.Usuario;

public class UsuarioRowMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(rs.getInt("idUsuario"));
		usuario.setNombre(rs.getString("nombre"));
		usuario.setApellido1(rs.getString("apellido1"));
		usuario.setApellido2(rs.getString("apellido2"));
		usuario.setUsername(rs.getString("username"));
		usuario.setPassword(rs.getString("password"));
		usuario.setRole(rs.getString("role"));
		usuario.setEmail(rs.getString("email"));
		return usuario;
	}

}
