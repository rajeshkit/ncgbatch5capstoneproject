package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdAlreadyExistsException;
import com.altimetrik.routemicroservice.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RouteServiceImpl implements RouteService {

    @Value("${route.microservice.base-url}")
    private String routeMicroserviceBaseUrl;
    public void printBaseUrl() {
        System.out.println("Route Microservice Base URL: " + routeMicroserviceBaseUrl);
    }
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) throws RouteIdAlreadyExistsException, RouteIdNotExistsException {

        if (routeRepository.existsById(route.getRouteId())) {
            throw new RouteIdAlreadyExistsException("Route ID " + route.getRouteId() + " already exists.");
        }
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(int routeId) throws RouteIdNotExistsException {

        return routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteIdNotExistsException("Route with ID " + routeId + " not found"));

    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistsException {
        if(getRouteById(route.getRouteId())!=null) {
            return routeRepository.save(route);
        }
        return null;
    }

    @Override
    public String deleteRouteById(int routeId) throws RouteIdNotExistsException {

        return routeRepository.findById(routeId)
                .map(existingSchedule -> {
                    routeRepository.deleteById(routeId);
                    return "Route Deleted successfully";
                })
                .orElseThrow(() -> new RouteIdNotExistsException("Route with ID " + routeId + " not found"));
    }
}
