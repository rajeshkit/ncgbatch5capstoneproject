package com.routemicroservice.service;

import com.routemicroservice.exception.RouteIdDoesNotExistException;
import com.routemicroservice.model.Route;
import com.routemicroservice.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{
    @Autowired
    private RouteRepository routeRepository;
    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Override
    public Route saveRouteDetails(Route route) {
        logger.info("Saving route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutesDetail() {
        logger.info("Fetching all routes.");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteDetailsById(int routeId) throws RouteIdDoesNotExistException {
        Optional<Route> routeOptional = routeRepository.findById(routeId);
        if (routeOptional.isEmpty()){
            logger.error("RouteIdDoesNotExistException occurred for Route-ID: {}", routeId);
            throw new RouteIdDoesNotExistException("The Route with the provided Route-ID does not exist, Try again.");
        }
        logger.info("Fetching route by ID: {}", routeId);
        return routeOptional.get();
    }

    @Override
    public Route updateRouteDetails(Route route) throws RouteIdDoesNotExistException {
        if(getRouteDetailsById(route.getRouteId())!=null) {
            logger.info("Updating route: {}", route);
            return routeRepository.save(route);
        }
        return null;
    }

    @Override
    public String deleteRouteDetails(int routeId) throws RouteIdDoesNotExistException {
        String message="Route does not exists for the given Route-ID.";
        Route route = getRouteDetailsById(routeId);
        if(route!=null){
            logger.info("Deleting route with ID: {}", routeId);
            routeRepository.deleteById(routeId);
            message="Route deleted successfully.";
            return message;
        }
        return message;
    }
}