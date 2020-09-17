-- Ejercicio propuesto

insert into jugador (nombre, puntos, id_entrenador)
values ('Roger Federer', 3300, 2);
insert into jugador (nombre, puntos, id_entrenador)
values ('Rafael Nadal', 3200, 1);
insert into jugador (nombre, puntos, id_entrenador)
values ('Fernando González', 1550, 2);

select *
from jugador j
         inner join entrenador e on e.id_entrenador = j.id_entrenador
where e.id_entrenador = 1
  and j.puntos > 1700;

select *
from jugador j
         left outer join entrenador e on j.id_entrenador = e.id_entrenador
where e.id_entrenador = 1
   or j.puntos > 1700;

update jugador
set puntos = puntos / 2
where puntos between 1600 and 2000;
