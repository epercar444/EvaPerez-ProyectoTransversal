create database proyecto0;
use proyecto0;
create table equipo (
idEquipo int auto_increment primary key,
Nombre varchar (50) not null,
anyo_creacion int (4)
);
create table prueba (
    idprueba int auto_increment primary key,
    idfase int not null,
    numequipos int not null,
    numjueces int not null,
    mejororador bit not null
);
create table puntuacion (
    idpuntuacion int auto_increment primary key,
    idequipo1 int not null,
    idequipo2 int not null,
    puntuacion1 int not null,
    puntuacion2 int not null,
    observacion1 varchar(255),
    observacion2 varchar(255),
    penalizacion1 varchar(255),
    penalizacion2 varchar(255),
    foreign key (idequipo1) references equipo(idequipo),
    foreign key (idequipo2) references equipo(idequipo)
);
create table participante (
idparticipante int auto_increment primary key,
nombre varchar(50),
idequipo int not null,
foreign key (idequipo) references equipo(idequipo)
);
create table debate (
iddebate int auto_increment primary key,
idequipo1 int not null,
idequipo2 int not null,
fase varchar(255) not null
);
create table cruce (
idcruce int auto_increment primary key,
idequipo1 int not null,
idequipo2 int not null,
observaciones varchar(255),
foreign key (idequipo1) references equipo(idequipo),
foreign key (idequipo2) references equipo(idequipo)
);
drop table equipo;
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Madrid', 1945);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Barcelona', 1933);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Betis', 1907);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Mallorca', 1978);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Las Palmas', 1898);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Girona', 1920);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Alavés', 1931);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Bilbao', 1955);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Valladolid', 1936);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Leganés', 1978);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Celta', 1888);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Villareal', 1938);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Valencia', 1895);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Real Sociedad', 1947);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Atlético de Madrid', 1874);
INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('Getafe', 1987);

INSERT INTO prueba (idfase, numequipos, numjueces, mejororador) VALUES (1, 2, 3, 1);
INSERT INTO prueba (idfase, numequipos, numjueces, mejororador) VALUES (1, 2, 3, 1);
INSERT INTO prueba (idfase, numequipos, numjueces, mejororador) VALUES (2, 4, 2, 0);
INSERT INTO prueba (idfase, numequipos, numjueces, mejororador) VALUES (3, 3, 1, 1);
INSERT INTO prueba (idfase, numequipos, numjueces, mejororador) VALUES (4, 5, 2, 0);
INSERT INTO prueba (idfase, numequipos, numjueces, mejororador) VALUES (5, 2, 3, 1);

INSERT INTO puntuacion (idequipo1, idequipo2, puntuacion1, puntuacion2, observacion1, observacion2, penalizacion1, penalizacion2) VALUES (1, 2, 85, 78, 'Buen uso de argumentos', 'Falta de claridad', 'Ninguna', 'Advertencia por tiempo');
INSERT INTO puntuacion (idequipo1, idequipo2, puntuacion1, puntuacion2, observacion1, observacion2, penalizacion1, penalizacion2) VALUES (3, 4, 92, 88, 'Muy persuasivo', 'Ligeramente repetitivo', 'Ninguna', 'Ninguna');
INSERT INTO puntuacion (idequipo1, idequipo2, puntuacion1, puntuacion2, observacion1, observacion2, penalizacion1, penalizacion2) VALUES (5, 6, 76, 81, 'Argumentos sólidos', 'Buena presentación', 'Advertencia leve', 'Ninguna');
INSERT INTO puntuacion (idequipo1, idequipo2, puntuacion1, puntuacion2, observacion1, observacion2, penalizacion1, penalizacion2) VALUES (7, 8, 89, 90, 'Excelente cohesión', 'Gran expresividad', 'Ninguna', 'Ninguna');
INSERT INTO puntuacion (idequipo1, idequipo2, puntuacion1, puntuacion2, observacion1, observacion2, penalizacion1, penalizacion2) VALUES (9, 10, 70, 75, 'Necesita mejorar estructura', 'Uso adecuado de ejemplos', 'Advertencia por interrupciones', 'Ninguna');
INSERT INTO puntuacion (idequipo1, idequipo2, puntuacion1, puntuacion2, observacion1, observacion2, penalizacion1, penalizacion2) VALUES (11, 12, 95, 93, 'Oratoria destacada', 'Muy buenos datos', 'Ninguna', 'Ninguna');


INSERT INTO participante (nombre, idequipo) VALUES ('Lucía Gómez', 1);
INSERT INTO participante (nombre, idequipo) VALUES ('Carlos Pérez', 2);
INSERT INTO participante (nombre, idequipo) VALUES ('Marta Ruiz', 1);
INSERT INTO participante (nombre, idequipo) VALUES ('Javier Torres', 3);
INSERT INTO participante (nombre, idequipo) VALUES ('Elena Díaz', 2);


alter table equipo ADD CONSTRAINT nombre_unico UNIQUE (Nombre);
alter table prueba ADD CONSTRAINT prueba_unica UNIQUE (idfase, idprueba);
alter table puntuacion ADD CONSTRAINT puntuacion_unico UNIQUE (idequipo1, idequipo2, puntuacion1, puntuacion2);
alter table cruce add constraint cruce_unico unique (idcruce,idequipo1,idequipo2);
alter table debate add constraint debate_unico unique (iddebate,idequipo1,idequipo2);

alter table puntuacion add column fase varchar(50);
alter table puntuacion add column idprueba int(3);
alter table puntuacion add column equipoGanador int (3);
alter table puntuacion add column mejorOrador varchar(50);
alter table puntuacion add column listaParticipantes1 varchar(255);
alter table puntuacion add column listaParticipantes2 varchar(255);

alter table prueba drop column idfase;
alter table prueba add column fase varchar(50);

ALTER TABLE Puntuacion ADD CONSTRAINT fk_mejorPuntuacion FOREIGN KEY (equipoGanador) REFERENCES Equipo(idEquipo);
alter table puntuacion add constraint fk_mejorOrador foreign key (idprueba) references prueba(idprueba);

select mejorOrador from prueba where idprueba = 1;
select idparticipante from participante where idequipo = 1;

ALTER TABLE puntuacion
DROP FOREIGN KEY fk_mejorPuntuacion;

ALTER TABLE Debate ADD CONSTRAINT fk_equipo1 FOREIGN KEY (idequipo1) REFERENCES Equipo(idEquipo);
ALTER TABLE Debate ADD CONSTRAINT fk_equipo2 FOREIGN KEY (idequipo2) REFERENCES Equipo(idEquipo);

INSERT INTO cruce (idequipo1, idequipo2, observaciones) VALUES (1, 2, 'Nada');
INSERT INTO cruce (idequipo1, idequipo2, observaciones) VALUES (3, 4, 'Nulo');
INSERT INTO cruce (idequipo1, idequipo2, observaciones) VALUES (1, 3, 'Verdad');
INSERT INTO cruce (idequipo1, idequipo2, observaciones) VALUES (2, 4, 'False');
INSERT INTO cruce (idequipo1, idequipo2, observaciones) VALUES (1, 4, 'True');

insert into debate (idequipo1,idequipo2,fase) values (1,2,'PrimeraFase');
insert into debate (idequipo1,idequipo2,fase) values (3,4,'SegundaFase');
insert into debate (idequipo1,idequipo2,fase) values (5,6,'Semifinal');
insert into debate (idequipo1,idequipo2,fase) values (7,8,'Final');
insert into debate (idequipo1,idequipo2,fase) values (7,4,'Semifinal');
INSERT INTO debate (idequipo1, idequipo2, fase) VALUES
-- PrimeraFase
(1, 2, 'PrimeraFase'),
(3, 4, 'PrimeraFase'),
(5, 6, 'PrimeraFase'),
(7, 8, 'PrimeraFase'),
(9, 10, 'PrimeraFase'),

-- SegundaFase
(11, 12, 'SegundaFase'),
(13, 14, 'SegundaFase'),
(15, 1, 'SegundaFase'),

-- Semifinal
(2, 3, 'Semifinal'),
(4, 5, 'Semifinal'),

-- Final
(6, 7, 'Final');

