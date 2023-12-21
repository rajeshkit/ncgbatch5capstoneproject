package com.railways.route.service;

import com.railways.route.exception.RouteIdExistsException;
import com.railways.route.exception.RouteNotFindException;
import com.railways.route.model.Route;
import com.railways.route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;
    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route addRoute(Route route) throws RouteIdExistsException {
        Optional<Route> r=routeRepository.findById(route.getRouteId());
        if(r.isEmpty())
        {
            return routeRepository.save(route);
        }
        else
        {
            throw new RouteIdExistsException("Route Id Exists!Enter Unique Route Id");
        }
    }

    @Override
    public List<Route> getRoute() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(Long routeId) throws RouteNotFindException {
        Optional<Route> route = routeRepository.findById(routeId);
        if (route.isEmpty()) {
            throw new RouteNotFindException("No Route With the selected id");
        }
        return route.get();
    }

    @Override
    public Route updateRoute(Route route) throws RouteNotFindException {
        if (getRouteById(route.getRouteId()) != null) {
            return routeRepository.save(route);
        }
        return null;
    }

    @Override
    public String deleteRoute(Long routeId) throws RouteNotFindException {
        String message = "Route Id doesn't Exist";
        Route route = getRouteById(routeId);
        if (route != null) {
            routeRepository.deleteById(routeId);
            message = "Route deleted successfully";
            return message;
        }
        return message;
    }
}
