package com.rajaparvathi.route.service;

import com.rajaparvathi.route.model.Route;
import com.rajaparvathi.route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImplement implements RouteService{
    @Autowired
    private RouteRepository routeRepository;
    @Override
    public Route addRouteDetails(Route route) {
        return routeRepository.save(route);
    }


    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route searchRouteById(int routeId) {
        Optional<Route> r = routeRepository.findById(routeId);
        return r.get();
    }

    @Override
    public Route updateDetails(Route route) {
        if(searchRouteById(Route.getRouteId())!=null){
            return routeRepository.save(route);
        }
        return null;
    }

    @Override
    public String removeRouteById(int routeId) {
        String message = "No such route found";
        Route r = searchRouteById(routeId);
        if(r!=null){
            routeRepository.deleteById(routeId);
            message = "Route Details Deleted Successfully";
            return message;
        }
        return message;
    }
}
