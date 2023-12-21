package com.Route.RouteMicroservice.service;

import com.Route.RouteMicroservice.entity.Route;
import com.Route.RouteMicroservice.exceptionhandling.RouteNotFoundException;
import com.Route.RouteMicroservice.repository.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RouteService {

   //@Autowired
    RouteRepository routeRepository;

    RouteService(RouteRepository routeRepository){
        this.routeRepository=routeRepository;
    }

    public Route addRoute(Route route) {

        log.info("adding the Route Object");
        return routeRepository.save(route);
    }

    public Route findRoute(int routeId) {
       log.info("finding the route for given routeId "+ routeId);
        Route route= routeRepository.findByRouteId(routeId);
        if(route==null)
            throw new RouteNotFoundException("Route Not Found with given route Id "+ routeId);
        return route;
    }

}
