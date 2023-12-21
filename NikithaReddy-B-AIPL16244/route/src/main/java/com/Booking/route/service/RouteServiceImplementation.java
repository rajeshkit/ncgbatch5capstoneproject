package com.Booking.route.service;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.route.model.RouteResources;
import com.Booking.route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImplementation implements RouteService {
    @Autowired
    private RouteRepository routeRepository;
    @Override
    public RouteResources addRoute(RouteResources routeResources) {
        return routeRepository.save(routeResources);

    }

    @Override
    public List<RouteResources> getRoute() {
        return routeRepository.findAll();
    }
    @Override
    public RouteResources getRouteById(Long routeId) throws RouteNotFindException {
        Optional<RouteResources> route = routeRepository.findById(routeId);
        if (route.isEmpty()) {
            throw new RouteNotFindException("No Route With the selected id");
        }
        return route.get();
    }

    @Override
    public RouteResources updateRoute(RouteResources routeResources) throws RouteNotFindException {
        if (getRouteById(routeResources.getRouteId()) != null) {
            return routeRepository.save(routeResources);
        }
        return null;
    }

    @Override
    public String deleteRoute(Long routeId) throws RouteNotFindException {
        String message = "Route Id doesn't Exist";
        RouteResources routeResources = getRouteById(routeId);
        if (routeResources != null) {
            routeRepository.deleteById(routeId);
            message = "Route deleted succesfully";
            return message;
        }
        return message;
    }

}
