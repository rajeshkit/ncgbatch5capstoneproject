create database scheduleService;

use scheduleService;



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
CREATE TABLE schedule (
    schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    departure_date_time DATETIME NOT NULL,
    arrival_date_time DATETIME NOT NULL,
    train_id INT NOT NULL,
    route_number INT NOT NULL,
    FOREIGN KEY (train_id) REFERENCES train(train_id),
    FOREIGN KEY (route_number) REFERENCES route(route_number)
);









select * from train;