-- SELECT * FROM EstadisticasCOVID.INS;

-- select count(departamento) from EstadisticasCOVID.INS where departamento='Valle del Cauca';

-- select count(atencionn) from EstadisticasCOVID.INS where atencionn='Recuperado';

-- select count(atencionn) from EstadisticasCOVID.INS where atencionn='Fallecido';

-- select count(atencionn) from EstadisticasCOVID.INS where atencionn='Hospital';

-- select count(atencionn) from EstadisticasCOVID.INS where atencionn='Casa';

-- select count(atencionn) from EstadisticasCOVID.INS where atencionn='Hospital UCI';

-- select count(sexo) from EstadisticasCOVID.INS where sexo='F';

-- select count(sexo) from EstadisticasCOVID.INS where sexo='M';

use EstadisticasCOVID;

drop procedure if exists RegistroHoy;

drop procedure if exists RegistroEdadHoy;

drop procedure if exists RegistroHistorico;

drop procedure if exists RegistroEdadHistorico;

delimiter #
create procedure RegistroHoy(
	in depart varchar(60)
)
begin
	insert into EstadisticasCOVID.Registro values (
		curdate(),
		depart,
		(select count(atencionn) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart),
		(select count(atencionn) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart),
		(select count(atencionn) from EstadisticasCOVID.INS where atencionn='Casa' and departamento=depart),
		(select count(atencionn) from EstadisticasCOVID.INS where atencionn='Hospital' and departamento=depart),
		(select count(atencionn) from EstadisticasCOVID.INS where atencionn='Hospital UCI'and departamento=depart),
		(select count(sexo) from EstadisticasCOVID.INS where sexo='F' and departamento=depart),
		(select count(sexo) from EstadisticasCOVID.INS where sexo='M' and departamento=depart),
		0
	);
	call RegistroEdadHoy(depart,num);
end #

delimiter #
create procedure RegistroEdadHoy( 
	in depart varchar(60)
)
begin
	insert into EstadisticasCOVID.confirmadosEdad values (
		curdate(),
		depart,
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad between 0 and 9),
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad between 10 and 19),
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad between 20 and 29),
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad between 30 and 39),
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad between 40 and 49),
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad between 50 and 59),
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad between 60 and 69),
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad between 70 and 79),
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad between 80 and 89),
		(select count(edad) from EstadisticasCOVID.INS where departamento=depart and edad > 90)
	);
	insert into EstadisticasCOVID.fallecidosEdad values (
		curdate(),
		depart,
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad between 0 and 9),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad between 10 and 19),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad between 20 and 29),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad between 30 and 39),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad between 40 and 49),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad between 50 and 59),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad between 60 and 69),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad between 70 and 79),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad between 80 and 89),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and edad > 90)
	);
	insert into EstadisticasCOVID.recuperadosEdad values (
		curdate(),
		depart,
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad between 0 and 9),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad between 10 and 19),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad between 20 and 29),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad between 30 and 39),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad between 40 and 49),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad between 50 and 59),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad between 60 and 69),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad between 70 and 79),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad between 80 and 89),
		(select count(edad) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and edad > 90)
	);
end #

delimiter #
create procedure RegistroHistorico(
	in depart varchar(60),
    in fecha DATE
)
begin
	insert into EstadisticasCOVID.Registro values (
		fecha,
		depart,
		(select count(atencionn) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha),
		(select count(atencionn) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha),
		(select count(atencionn) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and (fecha_recuperado > fecha or fecha_de_muerte > fecha or (atencionn='Casa' or atencionn='Hospital' or atencionn='Hospital UCI'))),
		0,
        0,
		(select count(sexo) from EstadisticasCOVID.INS where sexo='F' and departamento=depart and fecha_diagnostico <= fecha),
		(select count(sexo) from EstadisticasCOVID.INS where sexo='M' and departamento=depart and fecha_diagnostico <= fecha),
		0
	);
	call RegistroEdadHistorico(depart,fecha);
end #

delimiter #
create procedure RegistroEdadHistorico( 
	in depart varchar(60),
    in fecha date
)
begin
	insert into EstadisticasCOVID.confirmadosEdad values (
		fecha,
		depart,
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad between 0 and 9),
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad between 10 and 19),
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad between 20 and 29),
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad between 30 and 39),
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad between 40 and 49),
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad between 50 and 59),
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad between 60 and 69),
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad between 70 and 79),
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad between 80 and 89),
		(select count(*) from EstadisticasCOVID.INS where departamento=depart and fecha_diagnostico <= fecha and edad > 90)
	);
	insert into EstadisticasCOVID.fallecidosEdad values (
		fecha,
		depart,
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad between 0 and 9),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad between 10 and 19),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad between 20 and 29),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad between 30 and 39),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad between 40 and 49),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad between 50 and 59),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad between 60 and 69),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad between 70 and 79),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad between 80 and 89),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Fallecido' and departamento=depart and fecha_de_muerte <= fecha and edad > 90)
	);
	insert into EstadisticasCOVID.recuperadosEdad values (
		fecha,
		depart,
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad between 0 and 9),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad between 10 and 19),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad between 20 and 29),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad between 30 and 39),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad between 40 and 49),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad between 50 and 59),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad between 60 and 69),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad between 70 and 79),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad between 80 and 89),
		(select count(*) from EstadisticasCOVID.INS where atencionn='Recuperado' and departamento=depart and fecha_recuperado <= fecha and edad > 90)
	);
end #

