drop procedure if exists casosHoy;
drop procedure if exists hoyCol;
drop procedure if exists hoyBog;

delimiter #
create procedure casosHoy(
	in idM varchar(50)
)
begin
	declare n int;
    set n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa=idM);
    select idSubdivision, femeninos+masculinos, casa+hospital+uci, recuperados, fallecidos from Registro where idSubdivision in (select idSubdivision from Subdivision where idMapa=idM) order by fecha desc,idSubdivision desc limit n;
end #

delimiter #
create procedure hoyCol()
begin
	select *
	from (select Registro.idSubdivision,Registro.femeninos,Registro.masculinos,Registro.casa,Registro.hospital,Registro.uci,Registro.recuperados,Registro.fallecidos,
		confirmadosEdad.cero,confirmadosEdad.diez,confirmadosEdad.veinte,confirmadosEdad.treinta,confirmadosEdad.cuarenta,confirmadosEdad.cincuenta,confirmadosEdad.sesenta,confirmadosEdad.setenta,confirmadosEdad.ochenta,confirmadosEdad.noventa
		from EstadisticasCOVID.Registro, EstadisticasCOVID.confirmadosEdad
		where Registro.fecha = (select max(fecha) from EstadisticasCOVID.Registro where idSubdivision='Vichada') 
		and Registro.idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Colombia')
        and confirmadosEdad.fecha = (select max(fecha) from EstadisticasCOVID.confirmadosEdad where idSubdivision='Vichada') 
		and Registro.idSubdivision = confirmadosEdad.idSubdivision) as E

	join (select recuperadosEdad.idSubdivision,recuperadosEdad.cero as r0,recuperadosEdad.diez as r10,recuperadosEdad.veinte as r20,recuperadosEdad.treinta as r30,recuperadosEdad.cuarenta as r40,recuperadosEdad.cincuenta as r50,recuperadosEdad.sesenta as r60,recuperadosEdad.setenta as r70,recuperadosEdad.ochenta as r80,recuperadosEdad.noventa as r90,
		fallecidosEdad.cero,fallecidosEdad.diez,fallecidosEdad.veinte,fallecidosEdad.treinta,fallecidosEdad.cuarenta,fallecidosEdad.cincuenta,fallecidosEdad.sesenta,fallecidosEdad.setenta,fallecidosEdad.ochenta,fallecidosEdad.noventa
		from EstadisticasCOVID.recuperadosEdad, EstadisticasCOVID.fallecidosEdad
		where recuperadosEdad.fecha = (select max(fecha) from EstadisticasCOVID.recuperadosEdad where idSubdivision='Vichada') 
		and recuperadosEdad.idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Colombia') 
		and fallecidosEdad.fecha = (select max(fecha) from EstadisticasCOVID.fallecidosEdad where idSubdivision='Vichada') 
		and recuperadosEdad.idSubdivision = fallecidosEdad.idSubdivision) as F
		
	on E.idSubdivision = F.idSubdivision;
end #

delimiter #
create procedure hoyBog()
begin
	select *
	from (select Registro.idSubdivision,Registro.femeninos,Registro.masculinos,Registro.casa,Registro.hospital,Registro.uci,Registro.recuperados,Registro.fallecidos,
		confirmadosEdad.cero,confirmadosEdad.diez,confirmadosEdad.veinte,confirmadosEdad.treinta,confirmadosEdad.cuarenta,confirmadosEdad.cincuenta,confirmadosEdad.sesenta,confirmadosEdad.setenta,confirmadosEdad.ochenta,confirmadosEdad.noventa
		from EstadisticasCOVID.Registro, EstadisticasCOVID.confirmadosEdad
		where Registro.fecha = (select max(fecha) from EstadisticasCOVID.Registro where idSubdivision='Usme') 
		and Registro.idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Bogotá') 
        and confirmadosEdad.fecha = (select max(fecha) from EstadisticasCOVID.confirmadosEdad where idSubdivision='Usme') 
		and Registro.idSubdivision = confirmadosEdad.idSubdivision) as E

	join (select recuperadosEdad.idSubdivision,recuperadosEdad.cero as r0,recuperadosEdad.diez as r10,recuperadosEdad.veinte as r20,recuperadosEdad.treinta as r30,recuperadosEdad.cuarenta as r40,recuperadosEdad.cincuenta as r50,recuperadosEdad.sesenta as r60,recuperadosEdad.setenta as r70,recuperadosEdad.ochenta as r80,recuperadosEdad.noventa as r90,
		fallecidosEdad.cero,fallecidosEdad.diez,fallecidosEdad.veinte,fallecidosEdad.treinta,fallecidosEdad.cuarenta,fallecidosEdad.cincuenta,fallecidosEdad.sesenta,fallecidosEdad.setenta,fallecidosEdad.ochenta,fallecidosEdad.noventa
		from EstadisticasCOVID.recuperadosEdad, EstadisticasCOVID.fallecidosEdad
		where recuperadosEdad.fecha = (select max(fecha) from EstadisticasCOVID.recuperadosEdad where idSubdivision='Usme') 
		and recuperadosEdad.idSubdivision in (select idSubdivision from EstadisticasCOVID.Subdivision where idMapa='Bogotá') 
		and fallecidosEdad.fecha = (select max(fecha) from EstadisticasCOVID.fallecidosEdad where idSubdivision='Usme') 
		and recuperadosEdad.idSubdivision = fallecidosEdad.idSubdivision) as F
		
	on E.idSubdivision = F.idSubdivision;
end #

-- call casosHoy('Colombia');
-- call casosHoy('Bogotá');
-- call hoyCol();
-- call hoyBog();