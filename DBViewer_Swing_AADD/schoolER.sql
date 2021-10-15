SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS recibe;
DROP TABLE IF EXISTS alumno;
DROP TABLE IF EXISTS asignatura;
DROP TABLE IF EXISTS profesor;




/* Create Tables */

CREATE TABLE alumno
(
	nummatricula int(11) NOT NULL,
	nombre varchar(40),
	fechanacimiento date,
	telefono varchar(13),
	PRIMARY KEY (nummatricula)
);


CREATE TABLE asignatura
(
	codasignatura int(11) NOT NULL,
	nombre varchar(40),
	idprofesor int(11) NOT NULL,
	PRIMARY KEY (codasignatura)
);


CREATE TABLE profesor
(
	idprofesor int(11) NOT NULL,
	nif_p varchar(9),
	nombre varchar(40),
	especialidad varchar(40),
	PRIMARY KEY (idprofesor),
	UNIQUE (nif_p)
);


CREATE TABLE recibe
(
	nummatricula int(11) NOT NULL,
	codasignatura int(11) NOT NULL,
	cursoescolar varchar(40)
);



/* Create Foreign Keys */

ALTER TABLE recibe
	ADD FOREIGN KEY (nummatricula)
	REFERENCES alumno (nummatricula)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE recibe
	ADD FOREIGN KEY (codasignatura)
	REFERENCES asignatura (codasignatura)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE asignatura
	ADD FOREIGN KEY (idprofesor)
	REFERENCES profesor (idprofesor)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



