drop procedure if exists casosHoy; 

delimiter #
create procedure casosHoy(
	in idM varchar(50)
)
begin
	declare n int;
    set n = (select count(*) from EstadisticasCOVID.Subdivision where idMapa=idM);
    select idSubdivision,femeninos+masculinos,casa+hospital+uci,recuperados,fallecidos from Registro where idSubdivision in (select idSubdivision from Subdivision where idMapa=idM) order by fecha desc,idSubdivision desc limit n;
end #
call casosHoy('Colombia');
call casosHoy('Bogotá');