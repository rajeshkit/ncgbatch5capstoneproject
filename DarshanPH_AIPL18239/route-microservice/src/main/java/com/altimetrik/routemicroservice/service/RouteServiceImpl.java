package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.repository.RouteRepository;
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
        Route addedRoute = routeRepository.save(route);
        logger.info("Route added successfully: {}", addedRoute);
        return addedRoute;
    }

    @Override
    public List<Route> getAllRoute() {
        logger.info("Getting all routes");
        List<Route> routes = routeRepository.findAll();
        logger.info("Returning {} routes", routes.size());
        return routes;
    }

    @Override
    public Route getRouteById(int routeId) throws RouteIdNotExistsException {
        logger.info("Getting route by ID: {}", routeId);
        Optional<Route> rt = routeRepository.findById(routeId);
        if (rt.isEmpty()) {
            logger.error("Route ID {} does not exist", routeId);
            throw new RouteIdNotExistsException("Route Id does not exist in the db!!");
        }
        Route route = rt.get();
        logger.info("Returning route: {}", route);
        return route;
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistsException {
        logger.info("Updating route: {}", route);
        if (getRouteById(route.getRouteId()) != null) {
            Route updatedRoute = routeRepository.save(route);
            logger.info("Route updated successfully: {}", updatedRoute);
            return updatedRoute;
        }
        return null;
    }

    @Override
    public String deleteRouteById(int routeId) throws RouteIdNotExistsException {
        logger.info("Deleting route by ID: {}", routeId);
        String message = "Route does not Exist";
        if (getRouteById(routeId) != null) {
            routeRepository.deleteById(routeId);
            message = "Route deleted successfully";
            logger.info(message);
        } else {
            logger.error("Route ID {} does not exist", routeId);
        }
        return message;
    }
}
