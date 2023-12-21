package com.route.routemicroservice.service;

import com.route.routemicroservice.exception.RouteAlreadyExistsException;
import com.route.routemicroservice.exception.RouteNotFoundException;
import com.route.routemicroservice.exception.RouteOperationException;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.repository.RouteRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class RouteServiceImpl implements RouteService{
    @Autowired
    private RouteRepository routeRepository;
    @Override
    public Route addRoute(Route route) {
        if(routeRepository.existsById(route.getRouteId())){
            throw new RouteAlreadyExistsException("Route with ID "+route.getRouteId()+" already exists.");
        }
        log.info("inserting route into database");
       return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        log.info("fetching all the routes from database");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteByRouteId(int routeId) {
        Optional<Route> route=routeRepository.findById(routeId);
        log.info("fetching route by id from database");
        return route.orElseThrow(()->new RouteNotFoundException("There is no route found with this ID: "+ routeId));
    }

    @Override
    public Route updateRoute(Route route) {
        log.info("updating route in database");

        return routeRepository.save(route);
    }

    @Override
    public void deleteRouteByRouteId(int routeId) {
        if(!routeRepository.existsById(routeId)){
            throw new RouteOperationException("Route with ID "+routeId+" not found..! Deletion failed..!");
        }
        log.info("deleting route by id from database");
        routeRepository.deleteById(routeId);
    }
}
