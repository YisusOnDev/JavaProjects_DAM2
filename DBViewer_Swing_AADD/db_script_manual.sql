drop schema school;
create schema school;

use school;

create table profesor (
	idprofesor int(11) primary key,
	nif_p varchar(9) unique,
	nombre varchar(40),
	especialidad varchar(40),
	telefono varchar(13)
);

create table asignatura (
	codasignatura int(11) primary key,
	nombre varchar(40),
	idprofesor int(11),
	foreign key (idprofesor) references profesor(idprofesor)
);

create table alumno (
	nummatricula int(11) primary key,
	nombre varchar(40),
	fechanacimiento date,
	telefono varchar(13)
);

create table recibe (
	nummatricula int(11),
	codasignatura int(11),
	cursoescolar varchar(40),
	primary key (nummatricula, codasignatura),
	foreign key (nummatricula) references alumno(nummatricula),
	foreign key (codasignatura) references asignatura(codasignatura)
);
