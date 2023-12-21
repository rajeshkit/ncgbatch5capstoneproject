package com.altimetrik.route.routerestapi.service;

import com.altimetrik.route.routerestapi.exception.RouteNumberNotFoundException;
import com.altimetrik.route.routerestapi.model.Route;
import com.altimetrik.route.routerestapi.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {

        return routeRepository.save(route);

    }

    @Override
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(String routeId) throws RouteNumberNotFoundException {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        optionalRoute.orElseThrow(() -> new RouteNumberNotFoundException("Route Number " + routeId + " Not Found"));
        if(optionalRoute.isPresent()){
            return optionalRoute.get();
        }
        return null;
    }

    @Override
    public Route updateRoute(Route route, String routeId) throws RouteNumberNotFoundException {
        Route savedRoute = getRouteById(routeId);
        if (savedRoute == null) {
            throw new RouteNumberNotFoundException("Route Number " + routeId + " Not Found");
        } else {
            return routeRepository.save(route);
        }
    }

    @Override
    public String deleteRouteByID(String routeId) throws RouteNumberNotFoundException {

        String msg;
        Route route = getRouteById(routeId);
        if(route != null){
            routeRepository.deleteById(routeId);
            msg = "Route Details having Route Id "+routeId+" Deleted Successfully";
            return msg;
        }
        return null;
    }
}
