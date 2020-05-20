drop procedure if exists mayorCrecimiento;
delimiter #
create procedure mayorCrecimiento( 
    in fechaIN date
)
begin
	declare i int;
	set i = 0;
	drop table if exists promedios;
    
    create temporary table promedios(
		subdivision varchar(50) primary key,
        prom float 
    );
    
    set @deps = (select count(*) from estadisticascovid.Subdivision);
    
    while i < @deps do
		set @iDepart = (select idSubdivision from Subdivision limit i,1); 
		set @iFecha = date_add(fechaIN, interval -4 day);
		
		drop table if exists iDiferencias;  
		create temporary table iDiferencias(
			id int auto_increment primary key,
			dif int 
		);
		
		while @iFecha <= fechaIN do
			
			insert into iDiferencias (dif) values((select sum((select sum(masculinos + femeninos) from estadisticascovid.registro where fecha = @iFecha and idSubdivision = @iDepart)-(select sum(masculinos + femeninos) from estadisticascovid.registro where fecha = date_add(@iFecha, interval -1 day) and idSubdivision = @iDepart))));
			
			set @iFecha = date_add(@iFecha, interval 1 day);
		end while;
		
        -- select avg(dif) from iDiferencias;
		 insert into promedios values(@iDepart, (select avg(dif) from iDiferencias));
        select i;
		set i = i +1;
    end while;
   

	
    -- select * from iDiferencias;
	select * from promedios;
end #

call mayorCrecimiento (date('2020-04-01'));




