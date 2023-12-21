create database schedule;

create table schedule(scheduleid int not null auto_increment,
						departuredatetime datetime,
                        arrivaldatetime datetime,
                        trainnumber int,
                        route_id int,
                        constraint PK_schedule_scheduleid primary key (scheduleid),
                        constraint FK_schedule_trainnumber foreign key (trainnumber) references train(trainnumber),
						constraint FK_schedule_route_id foreign key (route_id) references route(route_id));


