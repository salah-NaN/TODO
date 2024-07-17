INSERT INTO users (name, username, password, street, city, country)
VALUES
    ('Juan Pérez', 'juanperez', 'password1', 'Calle Principal 123', 'Ciudad de México', 'México'),
    ('María López', 'marialopez', 'password2', 'Avenida Independencia 456', 'Buenos Aires', 'Argentina'),
    ('Carlos García', 'carlosgarcia', 'password3', 'Rua Principal 789', 'São Paulo', 'Brasil'),
    ('Ana Martínez', 'anamartinez', 'password4', 'Main Street 101', 'New York', 'United States'),
    ('Pedro Rodríguez', 'pedrorodriguez', 'password5', '123 Rue Principale', 'Paris', 'France'),
    ('Laura Fernández', 'laurafernandez', 'password6', 'Hauptstrasse 234', 'Berlin', 'Germany'),
    ('Javier Gutiérrez', 'javiergutierrez', 'password7', 'Calle Mayor 567', 'Madrid', 'España'),
    ('Sofía Sánchez', 'sofiasanchez', 'password8', 'Via Roma 789', 'Roma', 'Italia'),
    ('Diego Herrera', 'diegoherrera', 'password9', '123 Main Road', 'Sydney', 'Australia'),
    ('Isabel Medina', 'isabelmedina', 'password10', 'Rua Principal 345', 'Lisboa', 'Portugal');
    
    
    
    
    INSERT INTO todos (title, completed, user_id)
	VALUES
    ('Completar informe', true, 1),
    ('Preparar presentación', false, 2),
    ('Hacer ejercicio', false, 3),
    ('Comprar víveres', true, 4),
    ('Llamar al cliente', false, 5),
    ('Enviar correo electrónico', true, 6),
    ('Revisar documentos', false, 7),
    ('Organizar reunión', true, 8),
    ('Leer libro', false, 9),
    ('Planificar vacaciones', true, 10);