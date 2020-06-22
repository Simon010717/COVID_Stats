use EstadisticasCOVID;

/*
-- Tabla mapa
insert into EstadisticasCOVID.Mapa values ('Colombia'), ('Bogotá');

-- Tabla subdivision
insert into EstadisticasCOVID.Subdivision values ('Amazonas','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Antioquía','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Arauca','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Atlántico','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Barranquilla D.E.','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Bogotá D.C.','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Bolívar','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Boyacá','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Buenaventura D.E.','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Caldas','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Caquetá','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Cartagena D.T. y C.','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Casanare','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Cauca','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Cesar','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Chocó','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Córdoba','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Cundinamarca','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Guanía','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Guaviare','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Huila','Colombia');
insert into EstadisticasCOVID.Subdivision values ('La Guajira','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Magdalena','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Meta','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Nariño','Colombia');
insert into EstadisticasCOVID.Subdivision values ('N de Santander','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Putumayo','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Quindío','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Risaralda','Colombia');
insert into EstadisticasCOVID.Subdivision values ('San Andrés','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Santa Marta D.T. y C.','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Santander','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Sucre','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Tolima','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Valle del Cauca','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Vaupés','Colombia');
insert into EstadisticasCOVID.Subdivision values ('Vichada','Colombia');

insert into EstadisticasCOVID.Subdivision values ('Antonio Nariño','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Barrios Unidos','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Bosa','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Ciudad Bolivar','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Chapinero','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Engativá','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Fontibón','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Fuera','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Kennedy','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('La Candelaria','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Los Mártires','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Puente Aranda','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Rafael Uribe Uribe','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('San Cristobál','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Santa Fe','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Suba','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Sumapaz','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Teusaquillo','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Tunjuelito','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Usaquén','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Usme','Bogotá');
*/


-- Tablas registro
drop procedure if exists llenadoRegistrosCol1;
drop procedure if exists llenadoRegistrosCol2;
drop procedure if exists llenadoRegistrosBog;
drop procedure if exists actualizacionBog;
drop procedure if exists actualizacionCol;

delimiter #
create procedure llenadoRegistrosCol1()

begin
	declare i int;
	set i = 0;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Colombia') div 2;
	set @maxFecha = (select max(fecha_diagnostico) from EstadisticasCOVID.INS);
	set @minFecha = (select min(fecha_diagnostico) from EstadisticasCOVID.INS);
	select @maxFecha, @minFecha;
	while i < @n do
		set @iFecha = @minFecha;
		while @iFecha < @maxFecha do
			call RegistroHistoricoCol((select idSubdivision from Subdivision where idMapa='Colombia' limit i,1),@iFecha);
			set @iFecha = date_add(@iFecha, interval 1 day);
		end while;
        call RegistroHoyCol((select idSubdivision from Subdivision where idMapa='Colombia' limit i,1),@iFecha);
		set @iFecha = date_add(@iFecha, interval 1 day);
		select i;
		set i = i +1;
	end while;
	commit;
end #

delimiter #
create procedure llenadoRegistrosCol2()

begin
	declare i int;
	set i = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Colombia') div 2;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Colombia');
	set @maxFecha = (select max(fecha_diagnostico) from EstadisticasCOVID.INS);
	set @minFecha = (select min(fecha_diagnostico) from EstadisticasCOVID.INS);
	select @maxFecha, @minFecha;
	while i < @n do
		set @iFecha = @minFecha;
		while @iFecha < @maxFecha do
			call RegistroHistoricoCol((select idSubdivision from Subdivision where idMapa='Colombia' limit i,1),@iFecha);
			set @iFecha = date_add(@iFecha, interval 1 day);
		end while;
        call RegistroHoyCol((select idSubdivision from Subdivision where idMapa='Colombia' limit i,1),@iFecha);
		set @iFecha = date_add(@iFecha, interval 1 day);
		select i;
		set i = i +1;
	end while;
	commit;
end #


delimiter #
create procedure llenadoRegistrosBog()

begin
	declare i int;
	set i = 0;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Bogotá');
	set @maxFecha = (select max(fecha_diagnostico) from EstadisticasCOVID.Bogota);
	set @minFecha = (select min(fecha_diagnostico) from EstadisticasCOVID.Bogota);
	select @maxFecha, @minFecha;
	while i < @n do
		set @iFecha = @minFecha;
		while @iFecha < @maxFecha do
			call RegistroHistoricoBog((select idSubdivision from Subdivision where idMapa='Bogotá' limit i,1),@iFecha);
			set @iFecha = date_add(@iFecha, interval 1 day);
		end while;
        call RegistroHoyBog((select idSubdivision from Subdivision where idMapa='Bogotá' limit i,1),@iFecha);
		set @iFecha = date_add(@iFecha, interval 1 day);
		select i;
		set i = i +1;
	end while;
end #

delimiter #
create procedure actualizacionBog()

begin
	declare i int;
	set i = 0;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Bogotá');
    set @fechaBog = (select max(fecha_diagnostico) from EstadisticasCOVID.Bogota);
    set @fechaReg = (select max(fecha) from EstadisticasCOVID.Registro where idSubdivision='Usme');
    SET FOREIGN_KEY_CHECKS = 0; 
    delete from EstadisticasCOVID.confirmadosEdad where fecha >= @fechaReg and idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Bogotá');
    delete from EstadisticasCOVID.fallecidosEdad where fecha >= @fechaReg and idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Bogotá');
    delete from EstadisticasCOVID.recuperadosEdad where fecha >= @fechaReg and idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Bogotá');
    delete from EstadisticasCOVID.Registro where fecha >= @fechaReg and idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Bogotá');
    SET FOREIGN_KEY_CHECKS = 1;
	select @fechaBog, @fechaReg;
	while i < @n do
		set @iFecha = @fechaReg;
		while @iFecha < @fechaBog do
			call RegistroHistoricoBog((select idSubdivision from Subdivision where idMapa='Bogotá' limit i,1),@iFecha);
			set @iFecha = date_add(@iFecha, interval 1 day);
		end while;
        call RegistroHoyBog((select idSubdivision from Subdivision where idMapa='Bogotá' limit i,1),@iFecha);
		set @iFecha = date_add(@iFecha, interval 1 day);
		select i;
		set i = i +1;
	end while;
end # 

delimiter #
create procedure actualizacionCol()

begin
	declare i int;
	set i = 0;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Colombia');
    set @fechaCol = (select max(fecha_diagnostico) from EstadisticasCOVID.INS);
    set @fechaReg = (select max(fecha) from EstadisticasCOVID.Registro where idSubdivision='Vichada');
    SET FOREIGN_KEY_CHECKS = 0; 
    delete from EstadisticasCOVID.confirmadosEdad where fecha >= @fechaReg and idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Colombia');
    delete from EstadisticasCOVID.fallecidosEdad where fecha >= @fechaReg and idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Colombia');
    delete from EstadisticasCOVID.recuperadosEdad where fecha >= @fechaReg and idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Colombia');
    delete from EstadisticasCOVID.Registro where fecha >= @fechaReg and idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Colombia');
    SET FOREIGN_KEY_CHECKS = 1;
	select @fechaCol, @fechaReg;
	while i < @n do
		set @iFecha = @fechaReg;
		while @iFecha < @fechaCol do
			call RegistroHistoricoCol((select idSubdivision from Subdivision where idMapa='Colombia' limit i,1),@iFecha);
			set @iFecha = date_add(@iFecha, interval 1 day);
		end while;
        call RegistroHoyCol((select idSubdivision from Subdivision where idMapa='Colombia' limit i,1),@iFecha);
		set @iFecha = date_add(@iFecha, interval 1 day);
		select i;
		set i = i +1;
	end while;
end # 

-- call llenadoRegistrosCol();
-- call llenadoRegistrosBog();