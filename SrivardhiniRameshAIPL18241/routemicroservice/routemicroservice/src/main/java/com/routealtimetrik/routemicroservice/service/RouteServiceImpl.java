package com.routealtimetrik.routemicroservice.service;

import com.routealtimetrik.routemicroservice.exception.RouteIdAlreadyExistException;
import com.routealtimetrik.routemicroservice.exception.RouteIdNotExistException;
import com.routealtimetrik.routemicroservice.model.Route;
import com.routealtimetrik.routemicroservice.repository.RouteRepository;
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
    public Route addRoute(Route route) throws RouteIdAlreadyExistException {
        logger.info("Adding a new route: {}", route);
        if (routeRepository.existsById(route.getRouteId())) {
            throw new RouteIdAlreadyExistException(("Route with Id: "+route.getRouteId()+"already exist."));
        }
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        logger.info("Getting all routes");
        List<Route> allRoutes = routeRepository.findAll();
        if (allRoutes != null && !allRoutes.isEmpty()) {
            logger.info("Found {} routes", allRoutes.size());
        } else {
            logger.error("No routes found");
        }
        return allRoutes;
    }

    @Override
    public Route getRouteById(int routeId) throws RouteIdNotExistException {
        logger.info("Getting route by ID: {}", routeId);
        Optional<Route> routeOptional = routeRepository.findById(routeId);
        Route route = routeOptional.orElseThrow(() ->
                new RouteIdNotExistException("Route ID Does Not Exist In The Database!!!! Check The Route ID"));
        logger.info("Found route by ID {}: {}", routeId, route);
        return route;
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistException {
        logger.info("Updating route: {}", route);
        Route updatedRoute = getRouteById(route.getRouteId());
        if (updatedRoute != null) {
            updatedRoute = routeRepository.save(route);
            logger.info("Route updated successfully: {}", updatedRoute);
        } else {
            logger.error("Error updating route: {}", route);
        }
        return updatedRoute;
    }

    @Override
    public String deleteRouteById(int routeId) throws RouteIdNotExistException {
        logger.info("Deleting route by ID: {}", routeId);
        String message = "Route Does Not Exist";
        Route r = getRouteById(routeId);
        if (r != null) {
            routeRepository.deleteById(routeId);
            logger.info("Route {} has been deleted", routeId);
            return "Route Has Been Deleted " + routeId;
        } else {
            logger.error("Route deletion unsuccessful: {}", routeId);
            return message;
        }
    }
}
