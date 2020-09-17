-- Creación de tablas base

create table entrenador
(
    id_entrenador bigserial   not null
        constraint entrenador_pk
            primary key,
    nombre        varchar(50) not null
);

create table jugador
(
    id_jugador    bigserial   not null
        constraint jugador_pk
            primary key,
    nombre        varchar(50) not null,
    puntos        int,
    id_entrenador bigint,
    constraint jugador_entrenador_fk FOREIGN KEY (id_entrenador)
        references entrenador (id_entrenador)
);

create table cancha
(
    id_cancha bigserial   not null
        constraint cancha_pk
            primary key,
    nombre    varchar(60) not null,
    direccion varchar(60) not null
);

create table partido
(
    id_partido    bigserial   not null
        constraint partido_pk
            primary key,
    fechaComienzo date,
    estado        varchar(20) not null
);

-- Alteración para asignar relaciones

alter table partido
    add column id_jugador_local bigint;

alter table partido
    add constraint partido_jugador_local_fk
        foreign key (id_jugador_local)
            references jugador (id_jugador)
            on delete cascade
            on update cascade;

alter table partido
    add column id_jugador_visita bigint;

alter table partido
    add constraint partido_jugador_visita_fk
        foreign key (id_jugador_visita)
            references jugador (id_jugador)
            on delete cascade
            on update cascade;

alter table partido
    add column id_cancha bigint;

alter table partido
    add constraint partido_cancha_fk
        foreign key (id_cancha)
            references cancha (id_cancha)
            on delete cascade
            on update cascade;