package com.altimetrik.routemicroservice.service;
import com.altimetrik.routemicroservice.controller.RouteController;
import com.altimetrik.routemicroservice.exception.RouteIdNotExistException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
/*
>> @Service : annotation is primarily used to denote a class as a service component
in the Service layer of an application.This layer typically contains the business logic
of the application, handling complex processing, calculations, data manipulation,
and interaction with repositories or other services
 >> In this class we will write the whole implementation of the class including logic and functioning
    of the function.
 */
@Service
public class RouteServiceImplementation implements RouteService{

    @Autowired
    private RouteRepository routeRepository;
    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< addRouteDetail >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    @Override
    public Route addRouteDetail(Route route) {
//        System.out.println("Route Detail Added Successfully !!");
        logger.info("Received a request to Add Route Detail : {}", route);
        return routeRepository.save(route);
    }
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< getRouteById >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    @Override
    public Route getRouteById(int routeId) throws RouteIdNotExistException {
        Optional<Route> route = routeRepository.findById(routeId);
        //Now Check whether it contain something or not
        if(route.isEmpty())
        {
            throw new RouteIdNotExistException("Train Number Does not Exisit !!! Try Again");
        }
        logger.info("Received a request to get Route Detail by Id: {}", route);
        return route.get();
    }
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< updateRouteById >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    @Override
    public Route updateRouteById(Route route) throws RouteIdNotExistException {
        //Check whether the Route id Exist of not then if yes then user is able to update the details
        if(getRouteById(route.getRouteId())!=null)
        {
            return routeRepository.save(route);
        }
        logger.info("Received a request to update route Detail by Id : {}", route);
        return null;
        //If no Result found It will return null s
    }
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< deleteRouteById >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    @Override
    public String deleteRouteById(int routeId) throws RouteIdNotExistException {
        String message =  "Route Detail do not Exisit !! PLease Try Again";
        //Check whether the route is available or not
        Route route = getRouteById(routeId);
        //now check whether it is available or not
        if(route!=null)
        {
            //Delete the route user entered
            routeRepository.deleteById(routeId);
            message = "Route Deleted Successfully !!";
            return message;
        }
        logger.info("Received a request to delete route by Id : {}", routeId);
        return message;
    }
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Get allRouteDetail >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    @Override
    public List<Route> getAllRouteDetails()
    {
        logger.info("Received a request to get All Route Detail : {}");
        return  routeRepository.findAll();
    }
}
