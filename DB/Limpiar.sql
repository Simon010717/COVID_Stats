-- select * from INFORMATION_SCHEMA.TABLE_CONSTRAINTS where CONSTRAINT_TYPE = 'FOREIGN KEY';

-- select * from EstadisticasCOVID.INS;

SET FOREIGN_KEY_CHECKS = 0; 
truncate table EstadisticasCOVID.confirmadosEdad;
truncate table EstadisticasCOVID.fallecidosEdad;
truncate table EstadisticasCOVID.recuperadosEdad;
truncate table EstadisticasCOVID.Registro;
-- truncate table EstadisticasCOVID.Subdivision;
-- truncate table EstadisticasCOVID.Mapa;
SET FOREIGN_KEY_CHECKS = 1;
/*
insert into EstadisticasCOVID.Mapa values (1),(2);

SET FOREIGN_KEY_CHECKS = 0; 
truncate table EstadisticasCOVID.Subdivision;
SET FOREIGN_KEY_CHECKS = 1;

drop procedure if exists forloop;

delimiter #
create procedure forloop()
begin
declare cnt int unsigned default 0;
while cnt < 33 do
	insert into EstadisticasCOVID.Subdivision values (cnt,1);
    set cnt = cnt +1;
end while;
commit;
end #

call forloop();

drop procedure forloop;*/