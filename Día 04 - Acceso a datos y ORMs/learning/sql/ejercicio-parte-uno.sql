-- Ejercicio propuesto

insert into entrenador(nombre)
values ('Helena Nito');
insert into entrenador(nombre)
values ('Carmen Tita');

insert into jugador(nombre, puntos, id_entrenador)
values ('Josefa Rolito', 1800, 1);
insert into jugador (nombre, puntos, id_entrenador)
values ('Aquiles Baeza', 1500, 1);
insert into jugador(nombre, puntos, id_entrenador)
values ('Mario Neetha', 1800, 2);
insert into jugador(nombre, puntos)
values ('Nicolás Riera', 1200);

insert into cancha(nombre, direccion)
values ('Suzanne Chatrier', 'París');
insert into cancha(nombre, direccion)
values ('Philip Lenglen', 'París');

insert into partido(fechacomienzo, estado, id_jugador_local, id_jugador_visita, id_cancha)
values (now()::date, 'EN JUEGO', 1, 4, 1);
insert into partido(fechacomienzo, estado, id_jugador_local, id_jugador_visita, id_cancha)
values (now()::date, 'EN JUEGO', 3, 2, 2);

-- Sentencias de búsqueda básica
select *
from jugador;
select *
from entrenador;
select *
from cancha;
select *
from partido;

update partido
set estado = 'FINALIZADO';

insert into partido(fechacomienzo, estado, id_jugador_local, id_jugador_visita, id_cancha)
values (now()::date, 'FINALIZADO', 4, 2, 1);

update jugador
set puntos = puntos + 1000
where id_jugador = 4;