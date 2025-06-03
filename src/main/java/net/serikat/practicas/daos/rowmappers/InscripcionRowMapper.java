package net.serikat.practicas.daos.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.serikat.practicas.entities.Inscripcion;

public class InscripcionRowMapper implements RowMapper<Inscripcion> {

	@Override
	public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
		inscripcion.setIdInstalacion(rs.getInt("idInstalacion"));
		inscripcion.setIdCurso(rs.getInt("idCurso"));
        inscripcion.setHorario_inicio(rs.getTime("horario_inicio"));
        inscripcion.setHorario_fin(rs.getTime("horario_fin"));
        inscripcion.setIdMonitor(rs.getInt("idMonitor"));
        return inscripcion;
		
	}

}
