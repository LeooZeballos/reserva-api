-- Recursos
INSERT INTO RECURSO (NOMBRE, DESCRIPCION)
VALUES ('Proyector', 'Un proyector HD'),
       ('Pizarrón', 'Color negro'),
       ('Silla', 'Con cuatro patas'),
       ('Sillón', 'Color marrón'),
       ('Cañón', 'Un cañón de alta resolución'),
       ('Mesas', 'Mesas rectangulares para trabajo en equipo'),
       ('Pantalla', 'Pantalla de proyección'),
       ('Escritorio', 'Escritorio de madera para el docente'),
       ('Aire Acondicionado', 'Aire acondicionado para el aula');

-- Espacio Fisico
INSERT INTO ESPACIO_FISICO (NOMBRE, CAPACIDAD, DESCRIPCION, HABILITADO)
VALUES ('Aula 202', 20, '...', true),
       ('Aula 203', 30, '...', true),
       ('Aula 301', 40, '...', true),
       ('Aula 302', 25, '...', true),
       ('Aula 401', 15, '...', true),
       ('Laboratorio 101', 25, 'Laboratorio de informática', true),
       ('Sala de Conferencias', 50, 'Sala equipada para conferencias', true),
       ('Biblioteca', 100, 'Espacio para estudio y consulta de libros', true),
       ('Sala de Reuniones', 15, 'Sala para reuniones pequeñas', true),
       ('Auditorio', 200, 'Auditorio para eventos y presentaciones', true);

-- Recursos del Espacio Físico
INSERT INTO ESPACIO_FISICO_RECURSOS (ESPACIO_FISICO_ID, RECURSOS_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 4),
       (6, 5),
       (7, 6),
       (8, 7),
       (9, 8),
       (7, 9),
       (8, 9);

-- Estados
INSERT INTO ESTADO (NOMBRE, DESCRIPCION, COLOR)
VALUES ('Creado', 'La reserva fue creada de manera exitosa.', '#FFA500'),
       ('Reservado', 'La reserva quedó grabada correctamente.', '#008000'),
       ('Pendiente', 'La reserva está pendiente.', '#FFD700'),
       ('Cancelada', 'La reserva fue cancelada.', '#FF0000'),
       ('Completada', 'La reserva fue completada.', '#00FF00'),
       ('Bloqueada', 'La reserva fue bloqueada.', '#800000'),
       ('Rechazada', 'La reserva fue rechazada.', '#FF0000'),
       ('Finalizada', 'La reserva fue finalizada.', '#00FF00'),
       ('En Curso', 'La reserva está en curso.', '#008000'),
       ('Reprogramada', 'La reserva fue reprogramada.', '#FFD700'),
       ('Adelantada', 'La reserva fue adelantada.', '#FFD700'),
       ('Atrasada', 'La reserva fue atrasada.', '#FFD700'),
       ('Cerrada', 'La reserva fue cerrada.', '#00FF00'),
       ('En Proceso', 'La reserva está en proceso de confirmación.', '#FFA500'),
       ('En Espera', 'La reserva está en espera de aprobación.', '#FFD700'),
       ('Modificada', 'La reserva fue modificada.', '#FFD700'),
       ('En Ejecución', 'La reserva está en ejecución.', '#008000'),
       ('Suspendida', 'La reserva fue suspendida temporalmente.', '#800000'),
       ('Terminada', 'La reserva fue terminada.', '#00FF00'),
       ('No Realizada', 'La reserva no pudo ser realizada.', '#FF0000');

-- Rol
INSERT INTO ROL (NOMBRE, DESCRIPCION)
VALUES ('Administrador', 'Administrador del sistema.'),
       ('Docente', 'Docente de la universidad'),
       ('Alumno', 'Alumno de la universidad'),
       ('Encargado', 'Encargado de la administración de espacios'),
       ('Invitado', 'Invitado para eventos especiales');

-- Clientes
INSERT INTO CLIENTE (NOMBRE, APELLIDO, NRO_TELEFONO, EMAIL, DNI, ROL_ID)
VALUES ('Leonel', 'Zeballos', '12345678', 'zeballosleonel3@example.com', '12345678', 1),
       ('Juan', 'Perez', '12345678', 'juanperez@example.com', '12345678', 2),
       ('Pedro', 'Gonzalez', '12345678', 'pedrogonzalez@example.com', '12345678', 2),
       ('Maria', 'Gomez', '12345678', 'mariagomez@example.com', '12345678', 2),
       ('Jose', 'Rodriguez', '12345678', 'joserodriguez@example.com', '12345678', 2),
       ('Lucia', 'Fernandez', '12345678', 'luciafernandez@example.com', '12345678', 2),
       ('Laura', 'Lopez', '111223344', 'lauralopez@example.com', '98765432', 3),
       ('Martín', 'García', '2233445566', 'martingarcia@example.com', '87654321', 2),
       ('Ana', 'Martínez', '333444555', 'anamartinez@example.com', '76543210', 2),
       ('Diego', 'Fernández', '444555666', 'diegofernandez@example.com', '65432109', 2),
       ('Carolina', 'Gutiérrez', '555666777', 'carolinagutierrez@example.com', '54321098', 2),
       ('Federico', 'Sánchez', '666777888', 'federicosanchez@example.com', '43210987', 2);

-- Reservas
INSERT INTO RESERVA (FECHA_HORA_INICIO, FECHA_HORA_FIN, COMENTARIO, FECHA_HORA_CREACION, CLIENTE_ID,
                     ESTADO_ID, ESPACIO_FISICO_ID, MOTIVO_RESERVA)
VALUES ('2023-07-26 18:00:00', '2023-07-26 19:00:00', '...', NOW(), 2, 1, 1, 'Clase de Comunicaciones'),
       ('2023-07-26 18:00:00', '2023-07-26 19:00:00', '...', NOW(), 3, 1, 2, 'Clase de Redes'),
       ('2023-07-26 19:00:00', '2023-07-26 22:00:00', '...', NOW(), 2, 1, 1, 'Clase de Sistemas de Gestión'),
       ('2023-07-26 19:00:00', '2023-07-26 21:30:00', '...', NOW(), 4, 1, 4, 'Clase de Simulación'),
       ('2023-07-26 18:00:00', '2023-07-26 22:10:00', '...', NOW(), 5, 1, 3, 'Clase de Análisis de Sistemas'),
       ('2023-07-27 18:00:00', '2023-07-27 22:10:00', '...', NOW(), 5, 1, 4, 'Clase de Diseño de Sistemas'),
       ('2023-07-27 18:00:00', '2023-07-27 22:10:00', '...', NOW(), 2, 1, 1, 'Clase de Comunicaciones'),
       ('2023-07-27 18:00:00', '2023-07-27 22:10:00', '...', NOW(), 2, 1, 3, 'Clase de Redes'),
       ('2023-07-27 18:00:00', '2023-07-27 22:10:00', '...', NOW(), 2, 1, 2, 'Clase de Gestión de Datos'),
       ('2023-07-28 10:00:00', '2023-07-28 12:00:00', '...', NOW(), 2, 1, 2, 'Conferencia sobre Innovación'),
       ('2023-07-28 15:00:00', '2023-07-28 17:00:00', '...', NOW(), 3, 1, 3, 'Taller de Escritura Creativa'),
       ('2023-07-29 14:00:00', '2023-07-29 16:30:00', '...', NOW(), 4, 1, 4, 'Reunión de Proyecto'),
       ('2023-07-30 09:00:00', '2023-07-30 13:00:00', '...', NOW(), 5, 1, 5, 'Conferencia de Ciencias Ambientales'),
       ('2023-07-31 11:00:00', '2023-07-31 14:00:00', '...', NOW(), 6, 1, 1, 'Capacitación en Programación'),
       ('2023-08-01 18:00:00', '2023-08-01 20:30:00', '...', NOW(), 2, 1, 2, 'Charla sobre Inteligencia Artificial');