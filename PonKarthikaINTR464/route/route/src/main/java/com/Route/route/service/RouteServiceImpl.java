package com.Route.route.service;

import com.Route.route.exception.RouteIdNotFoundException;
import com.Route.route.model.Route;
import com.Route.route.repository.RouteRepository;
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
    public List<Route> getAllRoutes() {

        return routeRepository.findAll();
    }

    @Override
    public Optional<Route> getRouteById(int routeId) {

        return routeRepository.findById(routeId);
    }

    @Override
    public Route updateRoute(Route route) {

        if(getRouteById(route.getRouteId()).isPresent()) {
            return routeRepository.save(route);
        }
        return null;
    }
    @Override

    public String deleteRouteById(int routeId) throws RouteIdNotFoundException {
        String message="couldn't delete the route";
        Optional<Route> r=getRouteById(routeId);
        if(r.isPresent()){
            routeRepository.deleteById(routeId);
            message="route deleted successfully";
            return message;
        }
        return message;
    }

}
