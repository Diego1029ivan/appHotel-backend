/* clientes de hoteles */
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email,celular, estado_usuario) VALUES ('andres','$2a$10$eJlH0A9WjpVi4j4u1hvhweeW3ypyjIsKTcsNdfhPVnXYsV2d8KzbW',1, 'Andres', 'Guzman','profesor@bolsadeideas.com', 123456789, 1);
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email,celular, estado_usuario) VALUES ('juan','$2a$10$kgg1tiFnTJ7BGRHVWchF.eykCdxkAZP3Gf2FLeS91kY32SNTFsikW',1, 'Juan', 'Perez','juan@gmail.com', 123456789, 1);
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email,celular, estado_usuario) VALUES ('maria','$2a$10$LZ6dV38ilIl1yYQDLMOKDuCTcqX8oj48dkU477D3dhJQNv80MgrJG',1, 'Maria', 'Gomez','maria@gmail.com', 123456789, 1);
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email,celular, estado_usuario) VALUES ('pedro','$2a$10$5etgm9Wq1LGyXoOMStE4KuwN30BmOGUezOj0zyYcWXuoP1U5hiMB.',1, 'Pedro', 'Gonzalez','pedro@gmail.com', 123456789, 1);
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email,celular, estado_usuario) VALUES ('lucas','$2a$10$CEPldsQ7osLiIPsMeYbvjeGNCIMk.IJaakgarjICXcwESzQqyQKs2',1, 'Ponce de León', 'lucas','lucas@gmail.com', 123456789, 1);
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email,celular, estado_usuario) VALUES ('migel','$2a$10$/Ug9jC3TtCEdtsJOHeg8cO//ViHiCrybT3aZf7tjvKM91BJa.9DjK',1, 'migel', 'Andrade','migel@gmail.com', 123456789, 1);
/*Dueños de hoteles*/
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email,celular, estado_usuario) VALUES ('Sara','$2a$10$b43RTu5ChPD.9aEEzoHieeEThBPoR2WMpDxd7J3Skd82g8NpBTyxu',1, 'Sara', 'Benítez','sara@gmail.com', 123456789, 1);
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email, celular, estado_usuario) VALUES ('John','$2a$10$RFNjey3BSSioS5qpkcawDej9nBTU0qLzAg8Pz2hCqKyq/CmCks8h.',1, 'John', 'Castro','jhon.doe@bolsadeideas.com', 123456789, 1);
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email, celular, estado_usuario) VALUES ('Luis','$2a$10$JFXnS0C1GooKi4r6uThJbu495l19AMW9giJnDFVYvOQnVqDYebvhW',1, 'Luis', 'Gonzalez','luis@gmail.com', 123456789, 1);
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email, celular, estado_usuario) VALUES ('Carlos','$2a$10$VkXuymgWlpxna2WISBShY.OrjA1Wv7mG88P0tD8V041xoFhXakFuW',1, 'Carlos', 'Garcia','carlos@gmail.com', 123456789, 1);


/*roles*/
INSERT INTO `roles` (nombre_rol) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre_rol) VALUES ('ROLE_ADMIN');
INSERT INTO `roles` (nombre_rol) VALUES ('ROLE_SUPADMIN');

/* clientes de hoteles conrol user */
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (3, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (4, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (5, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (6, 1);
/*Dueños de hoteles con rol admin*/
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (7, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (8, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (9, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (10, 3);


/*Ubicaciones*/
INSERT INTO `ubicaciones` (ciudad, departamento, descripcion_ciudad, foto_ciudad, pais) VALUES ('Tarapoto','San Martín','ciudad de las palmeras', "tarapoto.jpg",'Peru')
INSERT INTO `ubicaciones` (ciudad, departamento, descripcion_ciudad, foto_ciudad, pais) VALUES ('Moyobamba','San Martín','ciudad de las orquideas', "moyo.jpg",'Peru')
INSERT INTO `ubicaciones` (ciudad, departamento, descripcion_ciudad, foto_ciudad, pais) VALUES ('Lamas','San Martín','ciudad de los tres pisos', "lamas.jpg",'Peru')

/*hoteles*/
INSERT INTO `hoteles` (cantidad_habitacion, descripcion_hotel, estado_hotel, logo, nombre_hotel, ruc, ubicacion_id, usuario_id) VALUES (10,'Lorem Ipsumis simply the filler text for printers and text files. Lorem Ipsum has been the standard filler text of industries since the 1500s,',1, 'SaraLogo3.png', 'Hotel de la Costa',1234567891,1,7);
INSERT INTO `hoteles` (cantidad_habitacion, descripcion_hotel, estado_hotel, logo, nombre_hotel, ruc, ubicacion_id, usuario_id) VALUES (20,'Lorem Ipsumis simply the filler text for printers and text files. Lorem Ipsum has been the standard filler text of industries since the 1500s,',1, 'logo2.png', 'Posada del Sol',1234577891,2,8);
INSERT INTO `hoteles` (cantidad_habitacion, descripcion_hotel, estado_hotel, logo, nombre_hotel, ruc, ubicacion_id, usuario_id) VALUES (20,'Lorem Ipsumis simply the filler text for printers and text files. Lorem Ipsum has been the standard filler text of industries since the 1500s,',1, 'logo3.png', 'Hotel Paramount',1234577891,3,9);
INSERT INTO `hoteles` (cantidad_habitacion, descripcion_hotel, estado_hotel, logo, nombre_hotel, ruc, ubicacion_id, usuario_id) VALUES (20,'Lorem Ipsumis simply the filler text for printers and text files. Lorem Ipsum has been the standard filler text of industries since the 1500s,',1, 'SaraLogo1.png', 'Malibú Hotel',1234577891,1,7);
INSERT INTO `hoteles` (cantidad_habitacion, descripcion_hotel, estado_hotel, logo, nombre_hotel, ruc, ubicacion_id, usuario_id) VALUES (20,'Lorem Ipsumis simply the filler text for printers and text files. Lorem Ipsum has been the standard filler text of industries since the 1500s,',1, 'SaraLogo2.png', 'Malibú Sucursal',1234577891,1,7);

/*rating*/
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 1, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 2, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 3, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 4, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 5, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 1, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 1, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 2, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 3, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 4, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 5, 1)
INSERT INTO `rating`(clasificacion,detalle,hoteles_id,usuario_id) VALUES (3.5,'buen hotel', 1, 1)
/*bar*/
INSERT INTO `bar` (nombrebar,descripcion_bar, foto_bar,hoteles_id) VALUES ('bar1','t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'bar.jpg',1);
INSERT INTO `bar` (nombrebar,descripcion_bar, foto_bar,hoteles_id) VALUES ('bar2','t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'bar2.jpg',1);
INSERT INTO `bar` (nombrebar,descripcion_bar, foto_bar,hoteles_id) VALUES ('bar3','t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'bar3.jpg',2);
INSERT INTO `bar` (nombrebar,descripcion_bar, foto_bar,hoteles_id) VALUES ('bar4','t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'bar4.jpg',2);
INSERT INTO `bar` (nombrebar,descripcion_bar, foto_bar,hoteles_id) VALUES ('bar5','t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'bar5.webp',3);
INSERT INTO `bar` (nombrebar,descripcion_bar, foto_bar,hoteles_id) VALUES ('bar6','t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'bar6.jpg',3);
INSERT INTO `bar` (nombrebar,descripcion_bar, foto_bar,hoteles_id) VALUES ('bar7','t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'bar7.webp',4);
INSERT INTO `bar` (nombrebar,descripcion_bar, foto_bar,hoteles_id) VALUES ('bar8','t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'bar8.jpg',4);

/*cocheras*/
INSERT INTO `cocheras` (descripcion_cochera, foto_cochera,hoteles_id) VALUES ('t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'cochera.jpg',1);
INSERT INTO `cocheras` (descripcion_cochera, foto_cochera,hoteles_id) VALUES ('t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'cochera2.jpg',2);
INSERT INTO `cocheras` (descripcion_cochera, foto_cochera,hoteles_id) VALUES ('t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'cochera3.jpg',3);
INSERT INTO `cocheras` (descripcion_cochera, foto_cochera,hoteles_id) VALUES ('t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is that it has a more or less normal distribution of letters', 'cochera4.jpg',4);


/*picinas*/
INSERT INTO `picinas` (descripcion_picina, foto_picina, horario_cierre, horario_inicio,precio_picina ,hoteles_id) VALUES ('t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is', 'picina.jpg','8','18',20,1);
INSERT INTO `picinas` (descripcion_picina, foto_picina, horario_cierre, horario_inicio,precio_picina,hoteles_id) VALUES ('t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is ', 'picina2.jpg','8','18',20,2);
INSERT INTO `picinas` (descripcion_picina, foto_picina, horario_cierre, horario_inicio,precio_picina,hoteles_id) VALUES ('t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is ', 'picina3.jpg','8','18',20,3);
INSERT INTO `picinas` (descripcion_picina, foto_picina, horario_cierre, horario_inicio,precio_picina,hoteles_id) VALUES ('t is an established fact too long ago that a reader will be distracted by the content of a sites text while looking at its design. The point of using Lorem Ipsum is', 'picina4.jpg','8','18',20,4);

/*Galeria*/
INSERT INTO `galerias` (foto, foto2, foto3,descripcionf1,descripcionf2,descripcionf3, hoteles_id, descripcion) VALUES ('foto2.jpg',"hotelOne1.jpg",'galeriaI1.jpg',"se muestra descripción de la foto 1","se muestra descripción de la foto 2","se muestra descripción de la foto 3", 1, "Lorem Ipsumis simply the filler text for printers and text files. Lorem Ipsum has been the standard filler text of industries since the 1500s, when an unknown");
INSERT INTO `galerias` (foto, foto2, foto3,descripcionf1,descripcionf2,descripcionf3, hoteles_id, descripcion) VALUES ('foto2.jpg',"hoteledo2.jpg",'galerial2.jpg',"se muestra descripción de la foto 1","se muestra descripción de la foto 2","se muestra descripción de la foto 3", 2, "Lorem Ipsumis simply the filler text for printers and text files. Lorem Ipsum has been the standard filler text of industries since the 1500s, when an unknown");
INSERT INTO `galerias` (foto, foto2, foto3,descripcionf1,descripcionf2,descripcionf3, hoteles_id, descripcion) VALUES ('foto2.jpg',"holteltri3.jpg",'galeria3.jpg',"se muestra descripción de la foto 1","se muestra descripción de la foto 2","se muestra descripción de la foto 3" ,3, "Lorem Ipsumis simply the filler text for printers and text files. Lorem Ipsum has been the standard filler text of industries since the 1500s, when an unknown");  
INSERT INTO `galerias` (foto, foto2, foto3,descripcionf1,descripcionf2,descripcionf3, hoteles_id, descripcion) VALUES ('foto.jpg',"hotelnuevosu.jpg",'galeria4.jpg',"se muestra descripción de la foto 1","se muestra descripción de la foto 2","se muestra descripción de la foto 3", 4, "Lorem Ipsumis simply the filler text for printers and text files. Lorem Ipsum has been the standard filler text of industries since the 1500s, when an unknown");
/*PrecioXTipodeHabitacion*/

INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Simple', 1, 40,10);
INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Doble', 1, 80,20);
INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Matrimonial', 1, 100,30);

INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Simple', 2, 40,10);
INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Doble', 2, 60,10);
INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Matrimonial', 2, 100,20);

INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Simple', 3, 40,20);
INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio,cantidad) VALUES ('Doble', 3, 85,20);
INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Matrimonial', 3, 120,20);

INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Simple', 4, 60,20);
INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Doble', 4, 96,20);
INSERT INTO `precioxtipo_habitaciones` (tipo_habitacion,  hoteles_id, precio, cantidad) VALUES ('Matrimonial', 4, 140,20);

/*Testimonios*/
INSERT INTO `testimonios` (detalle_testimonio,  usuario_id, estado,fecha) VALUES ('Esta página web de reservas de hoteles es lo máximo. Encontré el hotel perfecto en la ciudad que siempre quise visitar y pude reservar de manera fácil y rápida. ¡Recomiendo ampliamente este sitio web para planear tus próximas vacaciones!', 1,1,'2022-08-16');
INSERT INTO `testimonios` (detalle_testimonio,  usuario_id, estado,fecha) VALUES ('Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ullam enim magnam nihil? Soluta eius vel dolore iusto sed excepturi ducimus?', 2,1,'2022-08-16');
INSERT INTO `testimonios` (detalle_testimonio,  usuario_id, estado,fecha) VALUES ('Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ullam enim magnam nihil? Soluta eius vel dolore iusto sed excepturi ducimus?', 3,1,'2022-08-16');
INSERT INTO `testimonios` (detalle_testimonio,  usuario_id, estado,fecha) VALUES ('Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ullam enim magnam nihil? Soluta eius vel dolore iusto sed excepturi ducimus?', 1,1,'2022-08-16');
INSERT INTO `testimonios` (detalle_testimonio,  usuario_id, estado,fecha) VALUES ('No tenía mucho tiempo para planear mis vacaciones, pero esta página web me salvó la vida. Encontré el hotel perfecto y pude reservar con facilidad. Definitivamente utilizaré este sitio web de nuevo.', 1,1,'2022-08-16');
INSERT INTO `testimonios` (detalle_testimonio,  usuario_id, estado,fecha) VALUES ('Reservar con esta página web fue una experiencia increíble. Encontré una oferta de último momento en un hotel increíble y ahorré mucho dinero en mi estadía. ¡Gracias por hacer que mi viaje fuera inolvidable!', 2,1,'2022-08-16');
INSERT INTO `testimonios` (detalle_testimonio,  usuario_id, estado,fecha) VALUES ('Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ullam enim magnam nihil? Soluta eius vel dolore iusto sed excepturi ducimus?', 3,1,'2022-08-16');
INSERT INTO `testimonios` (detalle_testimonio,  usuario_id, estado,fecha) VALUES ('Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ullam enim magnam nihil? Soluta eius vel dolore iusto sed excepturi ducimus?', 1,1,'2022-08-16');

/**tipo de pago**/
INSERT INTO `tipo_pago` (detalle_tipo) VALUES ('Pago en efectivo');
INSERT INTO `tipo_pago` (detalle_tipo) VALUES ('Tarjeta de crédito');

//*Reservas*/
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-08-16','2022-08-17',500,1,2,1,1,1,2,3,120);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-07-15','2022-05-16',500,3,1,2,4,1,1,4,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-02-16','2022-08-17',500,2,3,1,2,1,2,5,150);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-01-16','2022-01-26',500,1,2,2,5,1,3,6,200);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-08-16','2022-09-07',500,2,1,1,3,1,1,2,160);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-06-16','2022-07-17',500,4,3,2,4,1,2,3,120);

INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-10-08','2022-10-09',455,2,2,1,1,1,3,3,120);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-10-20','2022-10-21',520,1,1,2,6,2,1,4,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-10-19','2022-10-20',100,1,3,1,2,1,2,4,180);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-10-18','2022-10-19',450,4,2,2,4,1,3,4,130);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-10-17','2022-10-18',320,1,1,1,3,2,1,2,110);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-10-16','2022-10-17',140,3,3,2,5,1,2,1,100);

INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-11-08','2022-10-09',455,3,2,1,1,1,1,2,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-11-20','2022-10-21',600,1,1,2,6,2,2,4,50);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-11-19','2022-10-20',100,1,3,1,2,1,3,5,180);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-11-18','2022-10-19',450,4,2,2,4,1,1,4,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-11-17','2022-10-18',363,2,1,1,3,2,2,1,105);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-11-16','2022-10-17',140,1,3,2,5,1,3,2,80);

INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-02-08','2022-02-09',455,1,2,1,1,1,1,4,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-02-20','2022-02-21',520,3,1,2,6,2,2,5,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-02-19','2022-02-20',200,1,3,1,2,1,3,4,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-02-18','2022-02-19',450,2,2,2,4,1,1,4,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-02-17','2022-02-18',320,1,1,1,5,2,2,2,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-02-16','2022-02-17',140,3,3,2,6,1,3,4,100);

INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-12-08','2022-12-09',455,1,2,1,3,1,1,3,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-12-20','2022-12-21',520,2,1,2,4,2,2,4,10);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-12-19','2022-12-20',550,1,3,1,1,1,3,4,120);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-12-18','2022-12-19',450,2,2,2,5,1,1,1,150);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-12-17','2022-12-18',320,1,1,1,2,2,2,4,100);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2022-12-16','2022-12-17',340,3,3,2,6,1,3,4,100);

INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2023-12-17','2022-12-18',320,1,1,1,3,2,1,5,200);
INSERT INTO `reservas_hoteles` (fecha_entrada,  fecha_salida, adelanto_reserva,hoteles_id,usuario_id,tipopago_id,precio_tipo_habitacion,estado,cantidad_hab,cantidad_dias,costo_total) VALUES ('2023-12-16','2022-12-17',340,3,3,2,4,1,2,4,180);