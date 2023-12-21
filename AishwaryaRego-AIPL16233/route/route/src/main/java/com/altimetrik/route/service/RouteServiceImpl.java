package com.altimetrik.route.service;

import com.altimetrik.route.exception.RouteAlreadyExistsException;
import com.altimetrik.route.exception.RouteIdNotExistsException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.repository.RouteRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger LOGGER = Logger.getLogger(RouteServiceImpl.class.getName());

    @Autowired
    private RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Route> getAllRoutes() {
        LOGGER.log(Level.INFO, "Fetching all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(int routeId) throws RouteIdNotExistsException {
        LOGGER.log(Level.INFO, "Fetching route by ID: {0}", routeId);
        Optional<Route> r = routeRepository.findById(routeId);
        if (r.isEmpty()) {
            LOGGER.log(Level.WARNING, "Route with ID {0} does not exist", routeId);
            throw new RouteIdNotExistsException("This Route does not exist");
        }
        return r.get();
    }

    @Override
    public Route addRoute(Route train) throws RouteAlreadyExistsException {
        LOGGER.log(Level.INFO, "Adding new train: {0}", train);

        // Check if the train with the same number already exists
        if (routeRepository.existsById(train.getRouteId())) {
            throw new RouteAlreadyExistsException("Route with number " + train.getRouteId() + " already exists.");
        }

        return routeRepository.save(train);
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistsException {
        LOGGER.log(Level.INFO, "Updating route: {0}", route);
        if (getRouteById(route.getRouteId()) != null) {
            return routeRepository.save(route);
        }
        LOGGER.log(Level.WARNING, "Route with ID {0} does not exist. Update failed.", route.getRouteId());
        return null;
    }

    @Override
    public String deleteRoute(int routeId) throws RouteIdNotExistsException {
        LOGGER.log(Level.INFO, "Deleting route with ID: {0}", routeId);
        String message = "Route does not exist";
        Route r = getRouteById(routeId);
        if (r != null) {
            routeRepository.deleteById(routeId);
            message = "Route is deleted successfully";
            return message;
        }
        LOGGER.log(Level.WARNING, "Route with ID {0} does not exist. Deletion failed.", routeId);
        return message;
    }
}
