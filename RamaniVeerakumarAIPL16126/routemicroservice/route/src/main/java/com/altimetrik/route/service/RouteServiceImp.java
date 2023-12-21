package com.altimetrik.route.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.altimetrik.route.repository.RouteRepository;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.exception.RouteIdDoNotExitsException;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImp implements RouteService {
    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImp.class);
    @Autowired
    private RouteRepository routeRepository;
    @Override
    public Route getRouteById(int routeId) throws RouteIdDoNotExitsException {
        logger.info("Getting route by ID: {}", routeId);
        Optional<Route> pro = routeRepository.findById(routeId);

        if (pro.isEmpty()) {
            logger.warn("Route with ID {} not found", routeId);
            throw new RouteIdDoNotExitsException("there is no route with this route id");
        }
        Route route=pro.get();
        logger.info("Found route: {}", route);
        return route;
    }

    @Override
    public Route addRoute(Route route) {
        logger.info("Adding new route: {}", route);
        return routeRepository.save(route);
    }
    @Override
    public List<Route> getAllRoutes() {
        logger.info("Getting all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route updateRoute(@Validated Route route) throws RouteIdDoNotExitsException {
        logger.info("Updating route with ID: {}", route.getRouteId());
        Route exitingRoute=routeRepository.findById(route.getRouteId()).orElse(null);
        if (exitingRoute== null) {
            logger.warn("Route with ID {} not found for update", route.getRouteId());
            throw new RouteIdDoNotExitsException("Route not found: "+route.getRouteId());
//            return routeRepository.save(route);
        }
        Route route1=routeRepository.save(route);
        logger.info("Route updated successfully: {}",route1);
        return route1;

    }
    @Override
    public String deleteRouteById(int routeId) throws RouteIdDoNotExitsException {
        String message = "Route Does not exists to delete";
        Route route = getRouteById(routeId);
        if (route != null) {
            routeRepository.deleteById(routeId);
            message = "Route deleted successfully";
            return message;
        }
        return message;
    }

}
