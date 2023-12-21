package com.route.routemicroservice.service;

import com.route.routemicroservice.exception.RouteAlreadyExistsException;
import com.route.routemicroservice.exception.RouteNotFoundException;
import com.route.routemicroservice.exception.RouteOperationException;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        if (routeRepository.existsById(route.getRouteId())) {
            logger.warn("Route with ID {} already exists. Unable to add.", route.getRouteId());
            throw new RouteAlreadyExistsException("Route with ID " + route.getRouteId() + " already exists.");
        }
        logger.info("Adding new route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        logger.info("Fetching all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteByRouteId(int routeId) {
        logger.info("Fetching route by ID: {}", routeId);
        return routeRepository.findById(routeId).orElseThrow(() ->
                new NoSuchElementException("Route not found with ID: " + routeId));
    }

    @Override
    public Route updateRoute(Route route) {
        if (getRouteByRouteId(route.getRouteId()) == null) {
            logger.warn("Route with ID {} not found. Unable to update.", route.getRouteId());
            throw new RouteNotFoundException("This Route ID is not found.");
        }
        logger.info("Updating route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public void deleteRouteByRouteId(int routeId) {
        if (!routeRepository.existsById(routeId)) {
            logger.warn("Route with ID {} not found. Deletion failed.", routeId);
            throw new RouteOperationException("Route with ID " + routeId + " not found. Deletion failed.");
        }
        logger.info("Deleting route with ID: {}", routeId);
        routeRepository.deleteById(routeId);
    }
}
