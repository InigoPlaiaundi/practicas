# Proyecto Polideportivo

Esta aplicación simula la gestión y mantenimiento de un sistema de polideportivos de una localidad.

---

## Tabla de Contenidos

- [Resumen](#resumen)
- [Funcionalidades](#funcionalidades)
- [Instalacion](#instalacion)
- [Uso](#uso)

---

## Resumen

La aplicación está diseñada para que los usuarios puedan inscribirse a diferentes cursos de los polideportivos disponibles.

La aplicación ha sido desarrollada en Eclipse junto a la tecnologia de Spring Framework.

---

## Funcionalidades

- **Iniciar sesion**:  
  _[El usuario al iniciar sesion dependiendo de su role puede acceder a distintas secciones]_

- **Darse de alta**:  
  _[El usuario elige el curso al cual se quiere inscribir y va acotando las inscripciones en base a los parametros que selecciona]_

- **Darse de baja**:  
  _[Eliminar la instancia que relaciona al usuario con la inscripcion.]_

- **Crear inscripción**:  
  _[Crear e insertar una nueva Inscripcion en la base de datos.]_

- **Modificar inscripción**:  
  _[Permite al usuario modificar los horarios y el monitor de una inscripcion.]_

- **Eliminar inscripción**:  
  _[Permite eliminar una inscripcion y a la hora de hacerlo notifica a todos los usuarios inscritos a la misma de la eliminacion]_

- **Crear usuario**:  
  _[Crear e insertar un nuevoUsuario en la base de datos.]_

- **Modificar usuario**:  
  _[Permite al usuario modificar los distintos parametros de los usuarios]_

- **Eliminar usuario**:  
  _[Permite al administrador eliminar usuarios, en caso de que el usuario tenga rol de monitor le impedira elimianrlo y mostrara los cursos que imparte]_

---

## Instalacion

 Requisitos Previos

- Java JDK 17 (o compatible con tu configuración)
- Maven instalado y configurado
- Eclipse, IntelliJ IDEA o cualquier IDE compatible con Spring

 Pasos

- Clona el repositorio:```bash git clone https://github.com/username/practicas.git cd practicas```

- Importa el proyecto en tu IDE:

Eclipse:
	Ve a File > Import > Maven > Existing Maven Projects, y selecciona la carpeta del proyecto.
	
IntelliJ IDEA:
	Ve a File > Open, selecciona la carpeta del proyecto y acepta importar como proyecto Maven.

- Compila el proyecto: `mvn clean install`
	
- Ejecuta: `mvn spring-boot:run`

---

## Uso

### Documentación de la API de Usuarios

Esta aplicación dispone de diferentes APIs para interactuar con la base de datos de usuarios y sus inscripciones.


 Endpoints disponibles

- **GET** `/practicas/api/usuarios/{id}`  
  Obtener usuario por ID

- **GET** `/practicas/api/usuarios/obtenerUsuario/{id}`  
  Obtener usuario mediante su email o username

- **GET** `/practicas/api/usuarios/all`  
  Obtener todos los usuarios

- **GET** `/practicas/api/usuarios/allMonitores`  
  Obtener todos los monitores

- **DELETE** `/practicas/api/usuarios/{id}`  
  Eliminar usuario por ID

- **PUT** `/practicas/api/usuarios/{id}`  
  Actualizar usuario por ID

- **POST** `/practicas/api/usuarios/insert`  
  Insertar un nuevo usuario

- **DELETE** `/practicas/api/usuarios/delete/{idUsuario}/{idInscripcion}`  
  Eliminar inscripción específica de un usuario

- **GET** `/practicas/api/usuarios/allUsuarioInscripciones/{idInscripcion}`  
  Obtener usuarios inscritos en una inscripción


[Markdown Cheat Sheet](https://www.markdownguide.org/cheat-sheet/)
