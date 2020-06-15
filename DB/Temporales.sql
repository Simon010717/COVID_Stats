drop procedure if exists graficasTemporales;
delimiter #
create procedure graficasTemporales(
	in idSub varchar(50)
)
begin
	select femeninos+masculinos,casa+hospital+uci,recuperados,fallecidos from EstadisticasCOVID.Registro where (idSubdivision = idSub) and  (fecha <= (select max(fecha) from EstadisticasCOVID.Registro) and fecha >= date_add((select max(fecha) from EstadisticasCOVID.Registro), interval -79 day));
end #

call graficasTemporales('Kennedy');
 
 select idSubdivision FROM EstadisticasCOVID.Subdivision where idMapa = 'Bogotá';
