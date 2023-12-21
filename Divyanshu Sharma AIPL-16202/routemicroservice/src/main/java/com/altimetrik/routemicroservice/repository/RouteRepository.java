package com.altimetrik.routemicroservice.repository;
import com.altimetrik.routemicroservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
/*
JpaRepository : JpaRepository is an interface provided by the Spring Data JPA framework whcih is used to perform CRUD operations
                that's why we can extend it to Repository and Also pass <POJO class name, PrimaryKey Datatype>
                in our case
                Class pojo class : Train
                Primary Key : trainNumber(Integer)

 */
public interface RouteRepository extends JpaRepository<Route, Integer> {
}
