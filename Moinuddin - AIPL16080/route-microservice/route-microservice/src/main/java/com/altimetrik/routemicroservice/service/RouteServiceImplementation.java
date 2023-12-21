package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exceptions.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;

import com.altimetrik.routemicroservice.repsitory.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImplementation implements RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImplementation.class);

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        logger.info("Adding route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        logger.info("Getting all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(String routeId) throws RouteIdNotExistsException {
        Optional<Route> routeOptional = routeRepository.findById(routeId);

        if (routeOptional.isEmpty()) {
            String errorMessage = "Route with ID " + routeId + " does not exist in the database.";
            logger.error(errorMessage);
            throw new RouteIdNotExistsException(errorMessage);
        }

        return routeOptional.get();
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistsException {
        if (!routeRepository.existsById(route.getRouteId())) {
            String errorMessage = "Route with ID " + route.getRouteId() + " does not exist in the database.";
            logger.error(errorMessage);
            throw new RouteIdNotExistsException(errorMessage);
        }

        logger.info("Updating route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public String deleteRouteById(String routeId) throws RouteIdNotExistsException {
        if (!routeRepository.existsById(routeId)) {
            String errorMessage = "Route with ID " + routeId + " does not exist in the database.";
            logger.error(errorMessage);
            throw new RouteIdNotExistsException(errorMessage);
        }

        routeRepository.deleteById(routeId);
        logger.info("Route with ID {} deleted successfully", routeId);
        return "Route deleted successfully";
    }
}
