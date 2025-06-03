package net.serikat.practicas.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.serikat.practicas.beans.Respuesta;
import net.serikat.practicas.entities.Usuario;
import net.serikat.practicas.facades.UsuarioFacade;

@SpringBootTest
@AutoConfigureMockMvc

//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	@Spy
	private UsuarioFacade usuarioFacade;

	@InjectMocks
	private UsuarioController usuarioController;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testGetUserById() throws Exception {
		
		Usuario usuarioMock = new Usuario();
		usuarioMock.setIdUsuario(1);
		usuarioMock.setNombre("Juan");
		
		//Mockito.when(usuarioFacade.getUserByid(Mockito.anyInt())).thenReturn(usuarioMock);
		
//		Mockito.when(usuarioFacade.getUserByid(Mockito.anyInt())).thenCallRealMethod();
		MvcResult result = mockMvc.perform(get("/api/usuarios/1"))
	            .andExpect(status().isOk())
	            .andDo(print())
	            .andReturn();
		

	    String json = result.getResponse().getContentAsString();

	    ObjectMapper mapper = new ObjectMapper();
	    Respuesta respuesta = mapper.readValue(json, Respuesta.class);
	    
	    Usuario usuario = respuesta.getUsuario();
	    
	    assertEquals(usuario.getNombre(), usuarioMock.getNombre());
	}

	@Test
	void testGetUserByEmail() throws Exception {
		ResultActions response = mockMvc.perform(get("/api/usuarios/obtenerUsuario/olaia45"));
		response.andExpect(status().isOk());
		response.andDo(print());
	}

	@Test
	void testGetAllUsers() throws Exception {
		ResultActions response = mockMvc.perform(get("/api/usuarios/all"));
		response.andExpect(status().isOk());
		response.andDo(print());
	}

	@Test
	void testGetAllMonitores() throws Exception {
		ResultActions response = mockMvc.perform(get("/api/usuarios/allMonitores"));
		response.andExpect(status().isOk());
		response.andDo(print());
	}

	@Transactional
	@Test
	void testDeleteUser() throws Exception {
		// ResultActions responseGet = mockMvc.perform(get("/api/usuarios/1"));
		ObjectMapper mapper = new ObjectMapper();

		// Obtener usuario a eliminar
		MvcResult resultGet = mockMvc.perform(get("/api/usuarios/6")).andExpect(status().isOk()).andReturn();

		String jsonGet = resultGet.getResponse().getContentAsString();

		Respuesta respuesta = mapper.readValue(jsonGet, Respuesta.class);

		Usuario usuarioAntes = respuesta.getUsuario();

		// Ejecutar el delete
		MvcResult resultDelete = mockMvc.perform(delete("/api/usuarios/6").header("user", "noninax"))
				.andExpect(status().isOk()).andReturn();


		// Comprobar si ha sido eliminado
		MvcResult resultGetInscripciones = mockMvc.perform(get("/api/usuarios/all")).andExpect(status().isOk())
				.andReturn();

		String jsonGetInscripciones = resultGetInscripciones.getResponse().getContentAsString();

		Respuesta respuestaGetUsuarios = mapper.readValue(jsonGetInscripciones, Respuesta.class);

		List<Usuario> usuarios = respuestaGetUsuarios.getListaUsuarios();

		for (Usuario usuario : usuarios) {
			if (usuario.getNombre().equals(usuarioAntes.getNombre())) {
				fail("boom");
			}
		}

	}
	@Transactional
	@Test
	void testUpdateUser() throws Exception {
        // Crear objeto Usuario con datos actualizados
        Usuario usuario = new Usuario();
        usuario.setNombre("Manuel");
        usuario.setApellido1("Alvarez");
        usuario.setApellido2("Lopez");
        usuario.setUsername("carlitrius");
        usuario.setPassword("0777");
        usuario.setRole("monitor");

        String json = objectMapper.writeValueAsString(usuario);

        mockMvc.perform(put("/api/usuarios/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario.nombre").value("Manuel"))
                .andDo(print());
	}

	@Transactional
	@Test
	void testCreateUser() throws Exception {
		
        Usuario usuario = new Usuario();
        usuario.setNombre("Aroa");
        usuario.setApellido1("Villena");
        usuario.setApellido2("Lopez");
        usuario.setUsername("villena52");
        usuario.setPassword("0777");
        usuario.setRole("monitor");

        String json = objectMapper.writeValueAsString(usuario);

        mockMvc.perform(post("/api/usuarios/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
				.andExpect(jsonPath("$.usuario.nombre").value("Aroa")).andDo(print());
	}

	@Test
	void testDeleteUserInscripcion() {
	}

}
