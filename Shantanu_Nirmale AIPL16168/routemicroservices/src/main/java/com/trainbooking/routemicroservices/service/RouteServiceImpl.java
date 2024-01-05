package com.trainbooking.routemicroservices.service;

import com.trainbooking.routemicroservices.exception.RouteIdNotExistException;
import com.trainbooking.routemicroservices.model.Route;
import com.trainbooking.routemicroservices.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    RouteRepository routeRepository;

    Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    public Route addRoute(Route route){
        Route route1 = routeRepository.save(route);
        logger.info("Route Added to the database is{}", route1);
        return route1;
    }

    public List<Route> getAllRoutes(){
        List<Route> allRoutes = routeRepository.findAll();
        logger.info("All routes are {}", allRoutes);
        return allRoutes;
    }

    public Route getRouteByRouteId(int routeId) throws RouteIdNotExistException {
        Optional<Route> route = routeRepository.findById(routeId);
        if(route.isPresent()){
            logger.info("Route Details Fetched{}",route.get());
            return route.get();
        } else {
            throw new RouteIdNotExistException("Route ID Not Exist !");
        }
    }

    public Route updateRouteDetails(Route route) throws RouteIdNotExistException{
        if(getRouteByRouteId(route.getRouteId())!=null){
            Route route1 = routeRepository.save(route);
            logger.info("Updated the route details");
            return route1;
        } else {
            throw new RouteIdNotExistException("Route Id Not Exist !");
        }
    }

    public String deleteRouteByRouteId(int routeId)throws RouteIdNotExistException{
        if(getRouteByRouteId(routeId)!=null){
            routeRepository.deleteById(routeId);
            logger.info("Route Deleted Successfully !!!");
            return "Route with RouteId : " + routeId + " Deleted Successfully !!!";
        } else {
            throw new RouteIdNotExistException("Route Id Not Exist !");
        }
    }


}
