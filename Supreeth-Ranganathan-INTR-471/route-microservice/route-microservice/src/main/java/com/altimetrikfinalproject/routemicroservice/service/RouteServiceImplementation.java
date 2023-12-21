package com.altimetrikfinalproject.routemicroservice.service;

import com.altimetrikfinalproject.routemicroservice.exception.RouteDoesNotExistException;
import com.altimetrikfinalproject.routemicroservice.model.Route;
import com.altimetrikfinalproject.routemicroservice.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImplementation implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }

    @Override
    public Optional<Route> getRouteById(int routeId) throws RouteDoesNotExistException {
        Optional<Route> route1 = routeRepository.findById(routeId);
        if(route1.isEmpty()){
            throw new RouteDoesNotExistException("Route Does Not Exist", "Please enter a valid ID");
        }
        return route1;
    }

    @Override
    public Route updateRoute(Route route) throws RouteDoesNotExistException {
        Optional<Route> route1 = routeRepository.findById(route.getRouteId());
        Route route2 = null;
        if(route1.isPresent()){
            route2 = route1.get();
        }
        route2.setDestination(route.getDestination());
        route2.setSource(route.getSource());
        route2.setTotalKms(route.getTotalKms());
        routeRepository.save(route2);
        return route2;
    }

    @Override
    public String deleteRoute(int routeId) throws RouteDoesNotExistException {
        Optional<Route> route1 = routeRepository.findById(routeId);
        if(route1.isEmpty()){
            throw new RouteDoesNotExistException("Route Does Not Exist", "Please enter a valid ID");
        }
        routeRepository.deleteById(routeId);
        return null;
    }
}
