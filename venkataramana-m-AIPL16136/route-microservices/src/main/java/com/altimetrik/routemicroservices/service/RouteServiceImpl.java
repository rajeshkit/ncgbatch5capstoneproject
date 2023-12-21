package com.altimetrik.routemicroservices.service;

import com.altimetrik.routemicroservices.exception.RouteIdAlreadyExistsException;
import com.altimetrik.routemicroservices.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservices.model.Route;
import com.altimetrik.routemicroservices.repository.RouteRepository;
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
    public Route addRoute(Route route) throws RouteIdAlreadyExistsException {
        logger.info("Adding route: {}", route);
        if (routeRepository.existsById(route.getRouteId())) {
            throw new RouteIdAlreadyExistsException("Train with ID " + route.getRouteId() + " already exists.");
        }
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        logger.info("Fetching all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(long routeId) throws RouteIdNotExistsException {
        logger.info("Fetching route by ID: {}", routeId);
        Optional<Route> route = routeRepository.findById(routeId);
        if (route.isEmpty()) {
            throw new RouteIdNotExistsException("Route ID does not exist");
        } else {
            return route.get();
        }
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistsException {
        logger.info("Updating route: {}", route);
        Route existingRoute = getRouteById(route.getRouteId());
        if (existingRoute != null) {
            return routeRepository.save(route);
        } else {
            throw new RouteIdNotExistsException("Route doesn't exist");
        }
    }

    @Override
    public String deleteRouteById(long routeId) throws RouteIdNotExistsException {
        logger.info("Deleting route by ID: {}", routeId);
        Route route = getRouteById(routeId);
        if (route != null) {
            routeRepository.deleteById(routeId);
            logger.info("Route deleted successfully");
            return "Deleted successfully";
        } else {
            throw new RouteIdNotExistsException("Route doesn't exist");
        }
    }

}
