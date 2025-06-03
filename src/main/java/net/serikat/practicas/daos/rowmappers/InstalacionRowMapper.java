package net.serikat.practicas.daos.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.serikat.practicas.entities.Instalacion;

public class InstalacionRowMapper implements RowMapper<Instalacion> {

	@Override
	public Instalacion mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		Instalacion instalacion = new Instalacion();
		instalacion.setIdInstalacion(rs.getInt("idInstalacion"));
		instalacion.setNombre(rs.getString("nombre"));
		instalacion.setDescripcion(rs.getString("descripcion"));
		instalacion.setResponsable(rs.getString("responsable"));
		instalacion.setCapacidad(rs.getInt("capacidad"));
		instalacion.setAforo(rs.getInt("aforo"));
		instalacion.setHorario_apertura(rs.getTime("horario_apertura"));
		instalacion.setHorario_cierre(rs.getTime("horario_cierre"));

		return instalacion;
	}

}
