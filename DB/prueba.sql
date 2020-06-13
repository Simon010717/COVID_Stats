-- suma de casa + hospital + uci -> activos
-- pasarle recuperados
-- 
-- select masculinos+femeninos from EstadisticasCOVID.Registro where idSubdivision = 'Kennedy' and fecha = select max(fecha) from EstadisticasCOVID.Registro;
select date_add((select max(fecha) from EstadisticasCOVID.Registro), interval -80 day);
select femeninos + masculinos from EstadisticasCOVID.Registro where (idSubdivision = 'Nariño') and  (fecha BETWEEN (select max(fecha) from EstadisticasCOVID.Registro) and date_add((select max(fecha) from EstadisticasCOVID.Registro), interval -80 day));
select femeninos+masculinos,casa+hospital+uci,recuperados,fallecidos from EstadisticasCOVID.Registro where (idSubdivision = 'Kennedy') and  (fecha <= (select max(fecha) from EstadisticasCOVID.Registro) and fecha >= date_add((select max(fecha) from EstadisticasCOVID.Registro), interval -80 day));