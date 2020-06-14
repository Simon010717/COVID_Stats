-- suma de casa + hospital + uci -> activos
-- pasarle recuperados
-- 
-- select masculinos+femeninos from EstadisticasCOVID.Registro where idSubdivision = 'Kennedy' and fecha = select max(fecha) from EstadisticasCOVID.Registro;
-- select date_add((select max(fecha) from EstadisticasCOVID.Registro), interval -80 day);
-- select femeninos + masculinos from EstadisticasCOVID.Registro where (idSubdivision = 'Nariño') and  (fecha BETWEEN (select max(fecha) from EstadisticasCOVID.Registro) and date_add((select max(fecha) from EstadisticasCOVID.Registro), interval -80 day));
drop procedure if exists graficasTemporales;
delimiter #
create procedure graficasTemporales(
	in idSub varchar(50)
)
begin
	select femeninos+masculinos,casa+hospital+uci,recuperados,fallecidos from EstadisticasCOVID.Registro where (idSubdivision = idSub) and  (fecha <= (select max(fecha) from EstadisticasCOVID.Registro) and fecha >= date_add((select max(fecha) from EstadisticasCOVID.Registro), interval -80 day));
end #

 call graficasTemporales('Barrios Unidos');
 
 select idSubdivision FROM EstadisticasCOVID.Subdivision where idMapa = 'Bogotá'
-- select count(femeninos+masculinos) from EstadisticasCOVID.Registro where (idSubdivision = 'Kennedy') and  (fecha <= (select max(fecha) from EstadisticasCOVID.Registro) and fecha >= date_add((select max(fecha) from EstadisticasCOVID.Registro), interval -80 day));
-- select idSubdivision FROM EstadisticasCOVID.Subdivision where idMapa = 'Bogotá';