create database route;
create table route(route_id int not null auto_increment,
					source varchar(50),
                    destination varchar(50),
                    total_Kms float,
					constraint PK_route_routeId primary key (route_id));
			
select * from route;            