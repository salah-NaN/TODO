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
