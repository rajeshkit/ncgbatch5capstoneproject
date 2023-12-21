create database trainMicroservice;

use trainMicroservice;

CREATE TABLE train (
    train_id INT PRIMARY KEY AUTO_INCREMENT,
    train_number INT NOT NULL,
    train_name VARCHAR(255) NOT NULL,
    total_kms DOUBLE NOT NULL,
    ac_coaches INT NOT NULL,
   ac_coach_total_seats INT NOT NULL,
    sleeper_coaches INT NOT NULL,
    sleeper_coach_total_seats INT NOT NULL,
    general_coaches INT NOT NULL,
    general_coach_total_seats INT NOT NULL
);


CREATE TABLE route (
    route_number INT PRIMARY KEY AUTO_INCREMENT,
    route_id long NOT NULL,
    source VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    total_kms DOUBLE NOT NULL
);

select * from route;
select * from train;


