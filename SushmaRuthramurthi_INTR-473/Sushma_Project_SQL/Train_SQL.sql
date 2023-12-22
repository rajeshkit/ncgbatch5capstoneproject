create database train;


create table coachtype(coachtypeid int not null auto_increment,
						coachnumber varchar(50),
                        seatcapacity int ,
                        constraint PK_coachtype_coachtypeid primary key (coachtypeid));
    select * from train;                    

create table train(trainnumber int not null auto_increment,
					trainname varchar(50),
                    constraint PK_train_trainnumber primary key (trainnumber));


create table train_coaches(id int not null auto_increment,
							trainnumber int,
                            coachtypeid int,
                            coachsize int,
                            constraint PK_traincoaches_id primary key (id),
                            constraint FK_traincoaches_trainnumber foreign key (trainnumber) references train(trainnumber),
                            constraint FK_traincoaches_coachtypeid foreign key (coachtypeid) references coachtype(coachtypeid));
                            
select * from train_coaches;
  