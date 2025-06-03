package net.serikat.practicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import net.serikat.practicas.entities.Curso;
import net.serikat.practicas.facades.CursoFacade;

@SpringBootTest
class CursoTest {

    @Autowired
    CursoFacade cursoFacade;

    @Test
    public void testObtenerCursos() {
        List<Curso> cursosTest = cursoFacade.obtenerCursos();
        assertNotNull(cursosTest);
    }

    @Test
    public void testGetCursoById() {
        Curso curso = new Curso();
        Curso cursoTest = new Curso();
        curso.setIdCurso(12);
        curso.setNombre("Pilates Avanzado");
        curso.setDescripcion("Clase de pilates para niveles avanzados");
        curso.setCapacidad(15);

        cursoTest = cursoFacade.getCursoById(12);

        assertEquals(curso.getIdCurso(), cursoTest.getIdCurso());
        assertEquals(curso.getNombre(), cursoTest.getNombre());
        assertEquals(curso.getDescripcion(), cursoTest.getDescripcion());
        assertEquals(curso.getCapacidad(), cursoTest.getCapacidad());
    }

	@Test
	@Transactional
    public void testInsertCurso() {
        Curso curso = new Curso();
        curso.setNombre("Pilates Avanzado");
        curso.setDescripcion("Clase de pilates para niveles avanzados");
        curso.setCapacidad(15);

        int numeroCursos = cursoFacade.contadorCurso();
        cursoFacade.insertCurso(curso);
        int numeroCursosDespues = cursoFacade.contadorCurso();
        assertEquals(numeroCursos + 1, numeroCursosDespues);
    }

	@Test
	@Transactional
    public void testUpdateCurso() {
        Curso curso = new Curso();
        curso.setIdCurso(17);
        curso.setNombre("Test update");
        curso.setDescripcion("comprobacion update");
        curso.setCapacidad(18);

        cursoFacade.updateCurso(curso);
        Curso cursoTest = new Curso();
        cursoTest = cursoFacade.getCursoById(17);

        assertEquals(curso.getIdCurso(), cursoTest.getIdCurso());
        assertEquals(curso.getNombre(), cursoTest.getNombre());
        assertEquals(curso.getDescripcion(), cursoTest.getDescripcion());
        assertEquals(curso.getCapacidad(), cursoTest.getCapacidad());
        
    }

	@Test
	@Transactional
    public void testDeleteCurso() {
        int idCurso = 20;
        boolean encontrado = false;
        cursoFacade.deleteCurso(idCurso);
        List<Curso> cursosTest = cursoFacade.obtenerCursos();
        for (Curso curso : cursosTest) {
            if (curso.getIdCurso() == idCurso) {
                encontrado = true;
            }
        }
        if (encontrado) {
            fail("Curso con ID " + idCurso + " no fue eliminado.");
        }
    }
}
