package com.altimetrik.route.service;
import com.altimetrik.route.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.route.exception.RouteNotExistsException;
import com.altimetrik.route.model.Route;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        logger.info("Adding route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllroute() {
        logger.info("Getting all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(int routeId) throws RouteNotExistsException {
        Optional<Route> pro = routeRepository.findById(routeId);

        if (pro.isEmpty()) {
            logger.error("Route with ID {} not found", routeId);
            throw new RouteNotExistsException("Route not exists in the database");
        }

        return pro.get();
    }

    @Override
    public Route updateRoute(Route route) throws RouteNotExistsException {
        if (getRouteById(route.getRouteId()) != null) {
            logger.info("Updating route: {}", route);
            return routeRepository.save(route);
        }
        return null;
    }

    @Override
    public String deleteRouteById(int routeId) throws RouteNotExistsException {
        String message = "Route Does not exists to delete";
        Route p = getRouteById(routeId);
        if (p != null) {
            logger.info("Deleting route with ID: {}", routeId);
            routeRepository.deleteById(routeId);
            message = "Route deleted successfully";
            return message;
        }
        return message;
    }
}