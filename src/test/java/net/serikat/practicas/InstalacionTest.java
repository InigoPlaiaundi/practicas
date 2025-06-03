package net.serikat.practicas;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import net.serikat.practicas.entities.Instalacion;
import net.serikat.practicas.facades.InstalacionFacade;

@SpringBootTest
class InstalacionTest {

	@Autowired
	InstalacionFacade instalacionFacade;

	@Test
	public void testObtenerInstalaciones() {
		List<Instalacion> instalacionesTest = instalacionFacade.obtenerInstalaciones();
		assertNotNull(instalacionesTest);
	}

	@Test
	public void testGetInstalacionById() {
		Instalacion instalacion = new Instalacion();
		instalacion.setIdInstalacion(12);
		instalacion.setNombre("Azken Portu");
		instalacion.setDescripcion("Centro deportivo de irun");
		instalacion.setResponsable("Xabier Aguirre");
		instalacion.setCapacidad(12);
		instalacion.setAforo(230);
		Time horario_apertura = Time.valueOf("05:00:00");
		instalacion.setHorario_apertura(horario_apertura);
		Time horario_cierre = Time.valueOf("00:00:00");
		instalacion.setHorario_cierre(horario_cierre);

		int id = instalacion.getIdInstalacion();

		Instalacion instalacionTest = instalacionFacade.getInstalacionByid(id);

		System.out.println(instalacion);
		System.out.println(instalacionTest);

		assertEquals(instalacion.getIdInstalacion(), instalacionTest.getIdInstalacion());
		assertEquals(instalacion.getNombre(), instalacionTest.getNombre());
		assertEquals(instalacion.getDescripcion(), instalacionTest.getDescripcion());
		assertEquals(instalacion.getResponsable(), instalacionTest.getResponsable());
		assertEquals(instalacion.getCapacidad(), instalacionTest.getCapacidad());
		assertEquals(instalacion.getAforo(), instalacionTest.getAforo());
		assertEquals(instalacion.getHorario_apertura(), instalacionTest.getHorario_apertura());
		assertEquals(instalacion.getHorario_cierre(), instalacionTest.getHorario_cierre());
	}
	
	@Test
	@Transactional
	public void testInsertInstalacion() {
		Instalacion instalacion = new Instalacion();
		instalacion.setNombre("Artaleku");
		instalacion.setDescripcion("Centro deportivo de irun");
		instalacion.setResponsable("Iñaki Mirena");
		instalacion.setCapacidad(12);
		instalacion.setAforo(230);
		Time horario_apertura = Time.valueOf("05:00:00");
		instalacion.setHorario_apertura(horario_apertura);
		Time horario_cierre = Time.valueOf("00:00:00");
		instalacion.setHorario_cierre(horario_cierre);

		instalacionFacade.insertInstalacion(instalacion);
		int numeroInstalaciones = instalacionFacade.contadorInstalacion();
		assertEquals(13, numeroInstalaciones);
	}

	@Test
	public void testUpdateInstalacion() {

		Instalacion instalacionTestBefore = instalacionFacade.getInstalacionByid(12);

		instalacionFacade.updateInstalacion(instalacionTestBefore);
		
		Instalacion instalacionTestAfter = instalacionFacade.getInstalacionByid(12);
		
		if (!instalacionTestAfter.getNombre().equals(instalacionTestBefore.getNombre())) {
			fail("La instalacion no se ha actualizado correctamente");
		}

	}

	@Test
	@Transactional
	public void testDeleteInstalacion() {
		int idInstalacion = 9;
		boolean encontrado = false;
		instalacionFacade.deleteInstalacion(idInstalacion);
		List<Instalacion> instalacionesTest = instalacionFacade.obtenerInstalaciones();
		for (Instalacion instalacion : instalacionesTest) {
			if (instalacion.getIdInstalacion() == idInstalacion) {
				encontrado = true;
			}

			if (encontrado) {
				fail();
			}
		}

	}

}
