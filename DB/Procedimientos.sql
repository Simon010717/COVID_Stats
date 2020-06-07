use EstadisticasCOVID;

drop procedure if exists RegistroHoyCol;

drop procedure if exists RegistroEdadHoyCol;

drop procedure if exists RegistroHoyBog;

drop procedure if exists RegistroEdadHoyBog;

drop procedure if exists RegistroHistoricoCol;

drop procedure if exists RegistroEdadHistoricoCol;

drop procedure if exists RegistroHistoricoBog;

drop procedure if exists RegistroEdadHistoricoBog;

drop procedure if exists hoyAyer;

delimiter #
create procedure RegistroHoyCol(
	in depart varchar(60),
    in fecha date
)
begin
	insert into EstadisticasCOVID.Registro values (
		fecha,
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
	call RegistroEdadHoyCol(depart,fecha);
end #

delimiter #
create procedure RegistroEdadHoyCol( 
	in depart varchar(60),
    in fecha date
)
begin
	insert into EstadisticasCOVID.confirmadosEdad values (
		fecha,
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
		fecha,
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
		fecha,
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
create procedure RegistroHoyBog(
	in depart varchar(60),
    in fecha date
)
begin
	insert into EstadisticasCOVID.Registro values (
		fecha,
		depart,
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Recuperado'),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido'),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado in ('Moderado','Severo','Crítico','No causa Directa)','Estudio') and ubicacion='Casa'),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado in ('Moderado','Severo','Crítico','No causa Directa)','Estudio') and ubicacion='Hospital'),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado in ('Moderado','Severo','Crítico','No causa Directa)','Estudio') and ubicacion='Hospital UCI'),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and sexo='F'),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and sexo='M'),
		0
	);
	call RegistroEdadHoyBog(depart,fecha);
end #

delimiter #
create procedure RegistroEdadHoyBog( 
	in depart varchar(60),
    in fecha date
)
begin
	insert into EstadisticasCOVID.confirmadosEdad values (
		fecha,
		depart,
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad between 0 and 9),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad between 10 and 19),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad between 20 and 29),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad between 30 and 39),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad between 40 and 49),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad between 50 and 59),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad between 60 and 69),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad between 70 and 79),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad between 80 and 89),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and edad > 90)
	);
	insert into EstadisticasCOVID.fallecidosEdad values (
		fecha,
		depart,
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad between 0 and 9),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad between 10 and 19),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad between 20 and 29),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad between 30 and 39),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad between 40 and 49),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad between 50 and 59),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad between 60 and 69),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad between 70 and 79),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad between 80 and 89),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and estado='Fallecido' and edad > 90)
	);
	insert into EstadisticasCOVID.recuperadosEdad values (
		fecha,
		depart,
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad between 0 and 9),
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad between 10 and 19),
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad between 20 and 29),
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad between 30 and 39),
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad between 40 and 49),
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad between 50 and 59),
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad between 60 and 69),
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad between 70 and 79),
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad between 80 and 89),
		(select count(*) from EstadisticasCOVID.Bogota where  localidad=depart and estado='Recuperado' and edad > 90)
	);
end #


delimiter #
create procedure RegistroHistoricoCol(
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
	call RegistroEdadHistoricoCol(depart,fecha);
end #

delimiter #
create procedure RegistroEdadHistoricoCol( 
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
	);/*
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
	);*/
end #

delimiter #
create procedure RegistroHistoricoBog(
	in depart varchar(60),
    in fecha DATE
)
begin
	insert into EstadisticasCOVID.Registro values (
		fecha,
		depart,
		0,
		0,
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha),
		0,
        0,
		(select count(sexo) from EstadisticasCOVID.Bogota where sexo='F' and localidad=depart and fecha_diagnostico <= fecha),
		(select count(sexo) from EstadisticasCOVID.Bogota where sexo='M' and localidad=depart and fecha_diagnostico <= fecha),
		0
	);
	call RegistroEdadHistoricoBog(depart,fecha);
end #

delimiter #
create procedure RegistroEdadHistoricoBog( 
	in depart varchar(60),
    in fecha date
)
begin
	insert into EstadisticasCOVID.confirmadosEdad values (
		fecha,
		depart,
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad between 0 and 9),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad between 10 and 19),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad between 20 and 29),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad between 30 and 39),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad between 40 and 49),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad between 50 and 59),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad between 60 and 69),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad between 70 and 79),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad between 80 and 89),
		(select count(*) from EstadisticasCOVID.Bogota where localidad=depart and fecha_diagnostico <= fecha and edad > 90)
	);/*
	insert into EstadisticasCOVID.fallecidosEdad values (
		fecha,
		depart,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0
	);
	insert into EstadisticasCOVID.recuperadosEdad values (
		fecha,
		depart,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0
	);*/
end #

delimiter #
create procedure hoyAyer()
begin
	declare i int;
    set @fecha = (select max(fecha) from Registro);
    select @fecha;
    -- BORRADO AYER
    SET FOREIGN_KEY_CHECKS = 0; 
	delete from confirmadosEdad where fecha = date_add(@fecha,interval -1 day);
	delete from fallecidosEdad where fecha = date_add(@fecha,interval -1 day);
	delete from recuperadosEdad where fecha = date_add(@fecha,interval -1 day);
	delete from Registro where fecha = date_add(@fecha,interval -1 day);
    delete from confirmadosEdad where fecha = @fecha;
	delete from fallecidosEdad where fecha = @fecha;
	delete from recuperadosEdad where fecha = @fecha;
	delete from Registro where fecha = @fecha;
	SET FOREIGN_KEY_CHECKS = 1;
	
    -- HISTORICO AYER
	set i = 0;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Colombia');
	while i < @n do
		call RegistroHistoricoCol((select idSubdivision from Subdivision where idMapa='Colombia' limit i,1),date_add(@fecha,interval -1 day));
		set i = i +1;
	end while;
    
    set i = 0;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Bogotá');
	while i < @n do
		call RegistroHistoricoBog((select idSubdivision from Subdivision where idMapa='Bogotá' limit i,1),date_add(@fecha,interval -1 day));
		set i = i +1;
	end while;
    
    -- HOY
    set i = 0;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Colombia');
	while i < @n do
		call RegistroHoyCol((select idSubdivision from Subdivision where idMapa='Colombia' limit i,1),@fecha);
		set i = i +1;
	end while;
    
    set i = 0;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Bogotá');
	while i < @n do
		call RegistroHoyBog((select idSubdivision from Subdivision where idMapa='Bogotá' limit i,1),@fecha);
		set i = i +1;
	end while;
end #

-- call hoyAyer();
