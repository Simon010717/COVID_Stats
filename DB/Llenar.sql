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
insert into EstadisticasCOVID.Subdivision values ('Teusaquillo','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Tunjuelito','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Usaquén','Bogotá');
insert into EstadisticasCOVID.Subdivision values ('Usme','Bogotá');
*/


-- Tablas registro
drop procedure if exists llenadoRegistrosCol;
drop procedure if exists llenadoRegistrosBog;

delimiter #
create procedure llenadoRegistrosCol()

begin
	declare i int;
	set i = 0;
	set @n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa='Colombia');
	set @maxFecha = (select max(fecha_diagnostico) from EstadisticasCOVID.INS);
	set @minFecha = (select min(fecha_diagnostico) from EstadisticasCOVID.INS);
	select @maxFecha, @minFecha;
	while i < @n do
		set @iFecha = @minFecha;
		while @iFecha <= @maxFecha do
			call RegistroHistoricoCol((select idSubdivision from Subdivision where idMapa='Colombia' limit i,1),@iFecha);
			set @iFecha = date_add(@iFecha, interval 1 day);
		end while;
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
		while @iFecha <= @maxFecha do
			call RegistroHistoricoBog((select idSubdivision from Subdivision where idMapa='Bogotá' limit i,1),@iFecha);
			set @iFecha = date_add(@iFecha, interval 1 day);
		end while;
		select i;
		set i = i +1;
	end while;
	commit;
end #

-- call llenadoRegistrosCol();
call llenadoRegistrosBog();