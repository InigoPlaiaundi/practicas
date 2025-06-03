package net.serikat.practicas.daos.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.serikat.practicas.entities.UsuarioInscripcion;

public class UsuarioInscripcionRowMapper implements RowMapper<UsuarioInscripcion> {

	@Override
	public UsuarioInscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UsuarioInscripcion usuarioInscripcion = new UsuarioInscripcion();
		usuarioInscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
		usuarioInscripcion.setIdUsuario(rs.getInt("idUsuario"));
		return usuarioInscripcion;
	}

}
