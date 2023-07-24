-- Recursos
INSERT INTO RECURSO (NOMBRE, DESCRIPCION)
VALUES ('Proyector', 'Un proyector HD'),
       ('Pizarrón', 'Color negro'),
       ('Silla', 'Con cuatro patas'),
       ('Sillón', 'Color marrón');

-- Espacio Fisico
INSERT INTO ESPACIO_FISICO (NOMBRE, CAPACIDAD, DESCRIPCION, HABILITADO)
VALUES ('Espacio 1', 20, '...', true),
       ('Espacio 2', 20, '...', true),
       ('Espacio 3', 20, '...', true),
       ('Espacio 4', 20, '...', true),
       ('Espacio 5', 20, '...', true);

-- Recursos del Espacio Físico
INSERT INTO ESPACIO_FISICO_RECURSOS (ESPACIO_FISICO_ID, RECURSOS_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 4);

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
       ('Cerrada', 'La reserva fue cerrada.', '#00FF00');

-- Rol
INSERT INTO ROL (NOMBRE, DESCRIPCION)
VALUES ('Administrador', 'Administrador del sistema.'),
       ('Usuario', 'Usuario del sistema.'),
       ('Cliente', ''),
       ('Docente', '');

-- Clientes
INSERT INTO CLIENTE (NOMBRE, APELLIDO, NRO_TELEFONO, EMAIL, DNI, ROL_ID)
VALUES ('Leonel', 'Zeballos', '12345678', 'zeballosleonel3@gmail.com', '12345678', 1),
       ('Juan', 'Perez', '12345678', 'juanperez@gmail.com', '12345678', 3),
       ('Pedro', 'Gonzalez', '12345678', 'pedrogonzalez@gmail.com', '12345678', 2),
       ('Maria', 'Gomez', '12345678', 'mariagomez@gmail.com', '12345678', 4);

-- Reservas
INSERT INTO RESERVA (FECHA_HORA_INICIO, FECHA_HORA_FIN, COMENTARIO, FECHA_HORA_CREACION, CLIENTE_ID, MOTIVO_RESERVA,
                     ESTADO_ID, ESPACIO_FISICO_ID)
VALUES ('2020-12-01 10:00:00', '2020-12-01 11:00:00', '...', '2020-12-01 10:00:00', 1, '...', 1, 1);
