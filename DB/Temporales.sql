drop procedure if exists temporalesCol;
drop procedure if exists temporalesBog;
drop procedure if exists fechaMax;
delimiter #
create procedure temporalesCol(
	in idSub varchar(50)
)
begin
	select femeninos+masculinos,casa+hospital+uci,recuperados,fallecidos from EstadisticasCOVID.Registro where (idSubdivision = idSub) and  (fecha >= date_add((select max(fecha) from EstadisticasCOVID.Registro where idSubdivision = 'Vichada'), interval -79 day));
end #


delimiter #
create procedure temporalesBog(
	in idSub varchar(50)
)
begin
	select femeninos+masculinos from EstadisticasCOVID.Registro where (idSubdivision = idSub) and  (fecha >= date_add((select max(fecha) from EstadisticasCOVID.Registro where idSubdivision = 'Usme'), interval -79 day));
end #

delimiter #
create procedure fechaMax(
	in idSub varchar(50)
)
begin
	select max(fecha) from EstadisticasCOVID.Registro where (idSubdivision = idSub);
end #
