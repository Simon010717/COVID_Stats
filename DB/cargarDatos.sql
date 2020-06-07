use EstadisticasCOVID;

drop table if exists Bogota;

create table if not exists Bogota(
	id int auto_increment  primary key,
	fecha_diagnostico varchar(50) not null,
    ciudad varchar(50) not null,
    localidad varchar(50) not null,
	edad int not null,
	sexo char not null,
    tipo varchar(50) not null,
    ubicacion varchar(50) not null,
    estado varchar(50) not null
);

LOAD DATA LOCAL INFILE 'datosBogota.csv' 
INTO TABLE Bogota 
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS (@fecha_diagnostico, ciudad, localidad, edad, sexo, tipo, ubicacion, estado)
SET fecha_diagnostico = str_to_date(@fecha_diagnostico, "%d/%m/%Y");

DROP TABLE IF EXISTS INS;

CREATE TABLE IF NOT EXISTS INS(
	id_de_caso INT NULL,
	fecha_de_notificacion DATE NULL,
	codigo_divipola INT NULL,
	ciudad_de_ubicacion VARCHAR(50) NULL,
	departamento VARCHAR(60) NULL,
	atencionn VARCHAR(50) NULL,
	edad INT NULL,
	sexo VARCHAR(50) NULL,
	tipo VARCHAR(50) NULL,
	estado VARCHAR(50) NULL,
	pais_de_procedencia VARCHAR(50) NULL,
	fis VARCHAR(50) NULL,
	fecha_de_muerte DATE NULL,
	fecha_diagnostico DATE NULL,
	fecha_recuperado DATE NULL,
	fecha_reporte_web DATE NULL,
	tipo_recuperacion varchar(50)
);

LOAD DATA LOCAL INFILE 'datosColombia.csv' 
INTO TABLE INS 
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

update EstadisticasCOVID.INS set fecha_diagnostico = fecha_de_notificacion where DATE_FORMAT(fecha_diagnostico, '%Y-%m-%d') = '0000-00-00';

call hoyAyer();