package com.altimetrik.trainroute.service;

import com.altimetrik.trainroute.exception.RouteIdNotExistsException;
import com.altimetrik.trainroute.model.Route;
import com.altimetrik.trainroute.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        logger.info("Adding new route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoute() {
        logger.info("Fetching all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(String routeId) throws RouteIdNotExistsException {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);

        if (optionalRoute.isEmpty()) {
            String errorMessage = "Route is not exists in the db!!! Check the route ID: " + routeId;
            logger.error(errorMessage);
            throw new RouteIdNotExistsException(errorMessage);
        }

        return optionalRoute.get();
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistsException {
        try {
            getRouteById(route.getRouteId());
            logger.info("Updating route: {}", route);
            return routeRepository.save(route);
        } catch (RouteIdNotExistsException e) {
            logger.error("Failed to update route. Route ID not found: {}", route.getRouteId());
            throw e;
        }
    }

    @Override
    public String deleteRouteById(String routeId) throws RouteIdNotExistsException {
        String message = "Route does not exist to delete";
        try {
            Route route = getRouteById(routeId);
            logger.info("Deleting route: {}", route);
            routeRepository.deleteById(routeId);
            message = "Route deleted successfully";
        } catch (RouteIdNotExistsException e) {
            logger.error("Failed to delete route. Route ID not found: {}", routeId);
            throw e;
        }
        return message;
    }
}
