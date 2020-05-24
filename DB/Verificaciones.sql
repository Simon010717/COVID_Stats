use EstadisticasCOVID;

drop procedure if exists verificarUsuario;

drop procedure if exists mayorCrecimiento;

drop procedure if exists masBuscados;

delimiter #
create procedure mayorCrecimiento( 
    in idM varchar(50)
)
begin
	declare i int;
    declare fechaIN date;
	set fechaIN = (select if(idM='Colombia',(select max(fecha_diagnostico) from EstadisticasCOVID.INS),(select max(fecha_diagnostico) from EstadisticasCOVID.Bogota)));
	
	set i = 0;
	drop table if exists promedios;
    
    create temporary table promedios(
		subdivision varchar(50) primary key,
        prom float 
    );
    
    set @subs = (select count(*) from EstadisticasCOVID.Subdivision where idMapa = idM);
    
    while i < @subs do
		set @iSubs = (select idSubdivision from Subdivision where idMapa = idM limit i,1); 
		set @iFecha = date_add(fechaIN, interval -4 day);
		
		drop table if exists iDiferencias;  
		create temporary table iDiferencias(
			id int auto_increment primary key,
			dif int 
		);
		
		while @iFecha <= fechaIN do
			
			insert into iDiferencias (dif) values((select sum((select sum(masculinos + femeninos) from EstadisticasCOVID.Registro where fecha = @iFecha and idSubdivision = @iSubs)-(select sum(masculinos + femeninos) from EstadisticasCOVID.Registro where fecha = date_add(@iFecha, interval -1 day) and idSubdivision = @iSubs))));
			
			set @iFecha = date_add(@iFecha, interval 1 day);
		end while;
		
        -- select avg(dif) from iDiferencias;
		 insert into promedios values(@iSubs, (select avg(dif) from iDiferencias));
        -- select i;
		set i = i +1;
    end while;
	drop table if exists resultados;
    
    create temporary table resultados(
		subs varchar(60)
	);
    
    
	insert into resultados values((select subdivision from promedios order by prom desc limit 0,1)) ;
    insert into resultados values((select subdivision from promedios order by prom desc limit 1,1)) ;
    insert into resultados values((select subdivision from promedios order by prom desc limit 2,1)) ;
    insert into resultados values((select subdivision from promedios order by prom asc limit 0,1)) ;
    insert into resultados values((select subdivision from promedios order by prom asc limit 1,1)) ;
    insert into resultados values((select subdivision from promedios order by prom asc limit 2,1)) ;
    -- insert into resultados(mins) values((select subdivision from promedios order by prom asc limit 0,1));
	select * from resultados;
   
end #


delimiter #
create procedure verificarUsuario(
	in nombre varchar(60),
    in pass varchar(60)
)
begin
select exists (select * from EstadisticasCOVID.Usuario where usuario=nombre and password = pass);
end #


delimiter #
create procedure masBuscados(
	in idM varchar(50)
)
begin
	-- declare n int;
	-- set n = (select count(*) from Subdivision where idMapa = idM);
	select idSubdivision, visitas 
	from Registro 
	where idSubdivision in (select idSubdivision from Subdivision where idMapa = idM) 
	order by fecha,visitas desc limit 3;
end




