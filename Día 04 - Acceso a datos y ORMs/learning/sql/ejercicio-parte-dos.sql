-- Ejercicio propuesto

select *
from jugador
         left join entrenador e on jugador.id_entrenador = e.id_entrenador;

select *
from jugador
         inner join entrenador e on jugador.id_entrenador = e.id_entrenador;

select *
from jugador j
         left join entrenador e on e.id_entrenador = j.id_entrenador
order by puntos desc, e.nombre;

select e.nombre as nombre_entrenador, sum(j.puntos) as puntos_totales
from jugador j
         left join entrenador e on j.id_entrenador = e.id_entrenador
group by e.nombre;