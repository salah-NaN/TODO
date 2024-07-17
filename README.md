# Proyecto TO-DO

![Screenshot 2024-07-17 at 17 51 32](https://github.com/user-attachments/assets/99ca6657-2070-44b2-b1de-5631af8ab23c)

## Descripción del Proyecto

Una aplicación de gestión de TO-DOs, es un CRUD completo con autenticación incluida.

## Guía de Usuario (HOW TO USE GUIDE)

### Requisitos Previos
- Docker Desktop instalado y ejecutándose.
- Docker Compose instalado
- Java 17 instalado.
- Maven instalado.

### Configuración y Ejecución

1. **Ejecutar Docker Compose**
   - Desde la raíz del proyecto, ejecutar el siguiente comando para levantar el contenedor de MySQL:
     ```
     docker-compose up -d
     ```

2. **Conectar al Contenedor MySQL**
   - Para acceder a la consola de MySQL dentro del contenedor:
     ```
     docker exec -it TODO-OMC mysql -u root -p
     ```
   - Ingresar la contraseña segura `1234`.

3. **Crear la Base de Datos**
   - Dentro de la consola de MySQL, crear la base de datos `todo`:
     ```
     CREATE DATABASE todo;
     ```

4. **Insertar Datos de Prueba**
   - Utilizar el script `fakeData.sql` proporcionado para insertar datos de prueba en las tablas correspondientes (cada una por separado, alternativamente se puede usar MySQLWorkbench).

5. **Salir del Contenedor MySQL**
   - Para salir de la consola de MySQL:
     ```
     exit
     ```

6. **Ejecutar la Aplicación Spring Boot**
   - Desde la raíz del proyecto, ejecutar el siguiente comando para iniciar la aplicación:
     ```
     ./mvnw spring-boot:run
     ```

7. **Ejecutar Tests Unitarios**
   - Para ejecutar los tests unitarios y verificar la funcionalidad:
     ```
     ./mvnw test
     ```

Si la aplicación reporta un error de que no se puede conectar a la base de datos, abrir MySQLWorkbench. Al menos es un bug que en mi caso ha pasado con otros proyectos, en este no debería. 













# Read Me First
The following was discovered as part of building this project:

* The original package name 'OMC-prueba.TODO' is invalid and this project uses 'OMC_prueba.TODO' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.1/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.1/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.3.1/reference/htmlsingle/index.html#web.servlet.spring-mvc.template-engines)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


