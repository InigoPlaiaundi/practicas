package net.serikat.practicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.System.Logger;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import net.serikat.practicas.beans.Filtro;
import net.serikat.practicas.beans.Respuesta;
import net.serikat.practicas.entities.Usuario;
import net.serikat.practicas.facades.UsuarioFacade;

@SpringBootTest
class ProyectoPracticasApplicationTests {

	@Autowired
	UsuarioFacade usuarioFacade;

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProyectoPracticasApplicationTests.class);

	@Test
	@Transactional
	public void deberiaInsertarPersona() {

		// El script de datos tiene 3 registros
		// assertEquals(2, usuarioFacade.contadorUsuarios());

		String username = RandomStringUtils.randomAlphabetic(10);

		Filtro usuario = new Filtro();
		usuario.setNombre("Juan");
		usuario.setUsername(username);
		usuario.setApellido1("Gomez");
		usuario.setApellido1("Jimenez");
		usuario.setPassword("1234");
		usuario.setRole("User");
		usuario.setEmail("juanitoJuan@gmail.com");

		usuarioFacade.insertUsuario(usuario);

		Usuario usuarioInsertado = usuarioFacade.getUser(username);
		assertNotNull(usuarioInsertado, "El usuario no fue insertado correctamente.");

	}

	@Test
	public void testGetUsuarioById() {
		int idUsuario = 2;
		Usuario usuario2 = usuarioFacade.getUserByid(idUsuario);
		assertEquals("Jose", usuario2.getNombre());
		System.out.println(usuario2);
	}

	@Test
	public void testObtenerUsuarios() {
		List<Usuario> usuariosTest = usuarioFacade.obtenerUsuarios();
		assertNotNull(usuariosTest);
	}
	
	@Test
	@Transactional
	public void testDeleteUsuario() {
		assertEquals(40, usuarioFacade.contadorUsuarios());
		int idUsuario = 7;
		usuarioFacade.deleteUsuario(idUsuario);
		assertEquals(39, usuarioFacade.contadorUsuarios());
	}

	@Test
	@Transactional
	public void testUpdateUsuario() {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(5);
		usuario.setNombre("Manex");
		usuario.setApellido1("Garciandia");
		usuario.setApellido2("Oyarzabal");
		usuario.setUsername(RandomStringUtils.random(10, true, false));
		usuario.setPassword("5434");
		usuario.setRole("Admin");
		usuario.setEmail("manexet@gmail.com");

		usuarioFacade.updateUsuario(usuario);
		Usuario usuarioTest = usuarioFacade.getUserByid(5);

		assertEquals("Manex", usuarioTest.getNombre());
	}

	@Test
	public void testBuscador() {

		final String nombre = "Elena";
		final String apellido1 = "Garcia";
		final String apellido2 = "Toro";
		final String username = "hsilva";
		final String role = "user";
		final String email = "elena@hotmail.com";

		// BUSCAR NOMBRE CON LIKE

		Filtro filtroTest = new Filtro();

		filtroTest.setNombre("ia");
		filtroTest.setContiene(1);

		List<Usuario> usuariosFiltrados = usuarioFacade.buscador(filtroTest);

		assertNotNull(usuariosFiltrados);

		for (Usuario usuario : usuariosFiltrados) {
			if (!usuario.getNombre().contains("ia")) {
				fail("el usuario no contiene las letras indicadas" + " " + usuario.getNombre());

			}
		}
		filtroTest = null;
		usuariosFiltrados = null;

		// BUSCAR NOMBRE CON WHERE

		filtroTest = new Filtro();

		filtroTest.setNombre(nombre);

		usuariosFiltrados = usuarioFacade.buscador(filtroTest);

		for (Usuario usuario : usuariosFiltrados) {
			if (!nombre.equals(usuario.getNombre().trim())) {
				fail("la busqueda no coincide con el nombre" + " " + usuariosFiltrados);
			} else {

			}
		}

		// BUSCAR APELLIDO1

		filtroTest = new Filtro();

		filtroTest.setApellido1(apellido1);

		usuariosFiltrados = usuarioFacade.buscador(filtroTest);

		assertNotNull(usuariosFiltrados);

		for (Usuario usuario : usuariosFiltrados) {
			if (!apellido1.equals(usuario.getApellido1())) {
				fail("El apellido de los usuario no coincide" + usuariosFiltrados);
			} else {

			}
		}

		// BUSCAR APELLIDO2

		filtroTest = new Filtro();

		filtroTest.setApellido2(apellido2);

		usuariosFiltrados = usuarioFacade.buscador(filtroTest);

		assertNotNull(usuariosFiltrados);

		for (Usuario usuario : usuariosFiltrados) {
			if (!apellido2.equals(usuario.getApellido2())) {
				fail("El apellido de los usuario no coincide" + usuariosFiltrados);
			} else {

			}
		}

		// BUSCAR USUARIO

		filtroTest = new Filtro();
		
		String usernameTest = "juanP"; 

		filtroTest.setUsername(username);

		usuariosFiltrados = usuarioFacade.buscador(filtroTest);

		assertNotNull(usuariosFiltrados);

		assertEquals(username, usuariosFiltrados.get(0).getUsername());

		// BUSCAR ROLE

		filtroTest = new Filtro();

		filtroTest.setRole(role);

		usuariosFiltrados = usuarioFacade.buscador(filtroTest);

		assertNotNull(usuariosFiltrados);

		for (Usuario usuario : usuariosFiltrados) {
			if (!role.equals(usuario.getRole())) {
				fail("los usuarios seleccionados no son admin" + usuariosFiltrados);
			} else {

			}
		}
		// BUSCAR EMAIL

		filtroTest = new Filtro();

		filtroTest.setEmail(email);

		usuariosFiltrados = usuarioFacade.buscador(filtroTest);

		assertNotNull(usuariosFiltrados);

		assertEquals(email, usuariosFiltrados.get(0).getEmail());

		// BUSQUEDA DE TODOS LOS CAMPOS

		filtroTest = new Filtro();

		filtroTest.setNombre(nombre);
		filtroTest.setApellido1(apellido1);
		filtroTest.setApellido2(apellido2);
		filtroTest.setUsername(username);
		filtroTest.setRole(role);
		filtroTest.setEmail(email);

		usuariosFiltrados = usuarioFacade.buscador(filtroTest);

		if (usuariosFiltrados.size() <= 0) {
			fail("no se han encontrado usuarios");
		}
		System.out.println(usuariosFiltrados + " ");

		for (Usuario usuario : usuariosFiltrados) {
			assertEquals(nombre, usuario.getNombre().trim(), "El nombre no coincide");
			assertEquals(apellido1, usuario.getApellido1().trim(), "El primer apellido no coincide");
			assertEquals(apellido2, usuario.getApellido2().trim(), "El segundo apellido no coincide");
			assertEquals(username, usuario.getUsername().trim(), "El username no coincide");
			assertEquals(role, usuario.getRole().trim(), "El rol no coincide");
			assertEquals(email, usuario.getEmail().trim(), "El email no coincide");
		}
	}
}
