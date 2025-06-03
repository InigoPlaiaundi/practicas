package net.serikat.practicas.controllers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.serikat.practicas.beans.RespuestaInscripcion;
import net.serikat.practicas.entities.Curso;
import net.serikat.practicas.entities.Inscripcion;
import net.serikat.practicas.entities.UsuarioInscripcion;

@SpringBootTest
@AutoConfigureMockMvc

class InscripcionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@InjectMocks
	private UsuarioController usuarioController;


	@Test
	void testGetUserById() throws Exception {
		Inscripcion inscripcionTest = new Inscripcion();
		
		 mockMvc.perform(get("/api/inscripciones/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.usuarioCompleto.usuario.idUsuario").value(1))
				.andDo(print());
	}

	@Test
	void testGetAllInscripciones() throws Exception {
		 mockMvc.perform(get("/api/inscripciones/all"))
			.andExpect(status().isOk())
		 	.andExpect(jsonPath("$.correcto").value(true));
	}

	@Test
	void testGetAlternativas() throws Exception {
		
		MvcResult resultGetAlternativas = mockMvc.perform(get("/api/inscripciones/6/1"))
		 .andExpect(status().isOk())
		 .andExpect(jsonPath("$.correcto").value(true))
		 .andReturn();
		
		String json = resultGetAlternativas.getResponse().getContentAsString();

	}

	@Transactional
	@Test
	void testCreateInscripcionUsuario() throws Exception {
		
		UsuarioInscripcion usrIns = new UsuarioInscripcion();
		usrIns.setIdInscripcion(7);
		usrIns.setIdUsuario(17);
		
		String json = objectMapper.writeValueAsString(usrIns);
		
		  mockMvc.perform(post("/api/inscripciones/insert")
				  .contentType(MediaType.APPLICATION_JSON)
		          .content(json))
				 .andExpect(status().isCreated())
				 .andExpect(jsonPath("$.usuarioCompleto.usuario.idUsuario").value(usrIns.getIdUsuario()));
	}

	@Transactional
	@Test
	void testCreateInscripcion() throws Exception {
		Inscripcion inscripcionTest = new Inscripcion();
		inscripcionTest.setIdCurso(6);
		inscripcionTest.setIdInstalacion(7);
		
		Time horario_inicio = Time.valueOf(LocalTime.parse("08:00:00"));
	    Time horario_fin = Time.valueOf(LocalTime.parse("20:00:00"));
		
		inscripcionTest.setHorario_inicio(horario_inicio);
		inscripcionTest.setHorario_fin(horario_fin);
		inscripcionTest.setIdMonitor(1);
		
		String json = objectMapper.writeValueAsString(inscripcionTest);
		
		  mockMvc.perform(post("/api/inscripciones/insertInscripcion")
				  .contentType(MediaType.APPLICATION_JSON)
		          .content(json))
				 .andExpect(status().isCreated())
				 .andExpect(jsonPath("$.correcto").value(true));
	}

	@Transactional
	@Test
	void testUpdateInscripcion() throws Exception {
		Inscripcion inscripcionTest = new Inscripcion();
		inscripcionTest.setIdCurso(2);
		inscripcionTest.setIdInstalacion(9);
		
		Time horario_inicio = Time.valueOf(LocalTime.parse("08:00:00"));
	    Time horario_fin = Time.valueOf(LocalTime.parse("22:00:00"));
		
		inscripcionTest.setHorario_inicio(horario_inicio);
		inscripcionTest.setHorario_fin(horario_fin);
		inscripcionTest.setIdMonitor(14);
		
		String json = objectMapper.writeValueAsString(inscripcionTest);
		
		  mockMvc.perform(put("/api/inscripciones/2")
				  .contentType(MediaType.APPLICATION_JSON)
		          .content(json))
				 .andExpect(status().isOk())
				 .andExpect(jsonPath("$.correcto").value(true));
		  		
	}
	
	@Transactional
	@Test
	void testDeleteInscripcion() throws Exception {
		int idInscripcion = 2;

		// Ejecutar DELETE
		mockMvc.perform(delete("/api/inscripciones/delete/" + idInscripcion))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.correcto").value(true));

		// Verificar que ya no exista llamando a GET
		mockMvc.perform(get("/api/inscripciones/" + idInscripcion))
				.andExpect(status().isNotFound())
				.andDo(print());
	}

	
	@Test
	void testGetAllCursos() throws Exception {
		  mockMvc.perform(get("/api/inscripciones/allCursos"))
				 .andExpect(status().isOk())
				 .andExpect(jsonPath("$.correcto").value(true));
	}

	@Transactional
	@Test
	void testDeleteCurso() throws Exception {

		int idCurso = 1;

		mockMvc.perform(delete("/api/inscripciones/" + idCurso)).andExpect(status().isOk())
				.andExpect(jsonPath("$.correcto").value(true)).andReturn();

		MvcResult resultGetAll = mockMvc.perform(get("/api/inscripciones/allCursos")).andExpect(status().isOk())
				.andExpect(jsonPath("$.correcto").value(true)).andReturn();

		String jsonGet = resultGetAll.getResponse().getContentAsString();
		RespuestaInscripcion respuesta = objectMapper.readValue(jsonGet, RespuestaInscripcion.class);

		List<Curso> listaCursos = respuesta.getListaCursos();

		for (Curso curso : listaCursos) {
			if (idCurso == curso.getIdCurso()) {
				fail("El curso no ha sido eliminado");
			}
		}

	}

	 @Test
	void testGetAllInstalaciones() throws Exception {
		  mockMvc.perform(get("/api/inscripciones/allInstalaciones"))
			 .andExpect(status().isOk())
			 .andExpect(jsonPath("$.correcto").value(true));
	}

	@Test
	void testGetAllMonitoresInscripciones() throws Exception {

		MvcResult resultGetAll = mockMvc.perform(get("/api/inscripciones/all"))
			.andExpect(status().isOk())
		 	.andExpect(jsonPath("$.correcto").value(true))
		 	.andReturn();

		String jsonGet = resultGetAll.getResponse().getContentAsString();
		RespuestaInscripcion respuesta = objectMapper.readValue(jsonGet, RespuestaInscripcion.class);
		Inscripcion inscripcionTest = respuesta.getListaAlternativas().get(0);
		
		String json = objectMapper.writeValueAsString(inscripcionTest);

		mockMvc.perform(
				post("/api/inscripciones/monitoresInscripciones").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.correcto").value(true))
				.andExpect(jsonPath("$.listaAlternativas").isArray())
				.andExpect(jsonPath("$.listaAlternativas.length()").value(Matchers.greaterThan(0)));
	}

}
