use EstadisticasCOVID;

drop table if exists Bogota;

create table if not exists Bogota(
	fecha_diagnostico varchar(50) not null,
    ciudad varchar(50) not null,
    localidad varchar(50) not null,
	edad int not null,
	sexo char not null,
    tipo varchar(50) not null,
    ubicacion varchar(50) not null,
    estado varchar(50) not null
);

#select * from Bogota;
#delete from Bogota;
#delete from INS;
#select * from INS;

#insert into Bogota values('2020-03-06', 'Bogotá','Usaquén','19','F','Importado','Casa', 'Recuperado');