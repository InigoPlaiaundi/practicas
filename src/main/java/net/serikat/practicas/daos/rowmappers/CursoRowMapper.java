package net.serikat.practicas.daos.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.serikat.practicas.entities.Curso;

public class CursoRowMapper implements RowMapper<Curso> {

	@Override
	public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Curso curso = new Curso();

		curso.setIdCurso(rs.getInt("idCurso"));
		curso.setNombre(rs.getString("nombre"));
		curso.setDescripcion(rs.getString("descripcion"));
		curso.setCapacidad(rs.getInt("capacidad"));

		return curso;
	}

}
