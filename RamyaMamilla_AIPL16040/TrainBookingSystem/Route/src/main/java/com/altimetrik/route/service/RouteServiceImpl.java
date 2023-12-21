package com.altimetrik.route.service;

import com.altimetrik.route.exception.RouteIDNotFoundException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.repository.RouteRepository;
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
    public List<Route> viewAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(int routeId) throws RouteIDNotFoundException {
        Optional<Route> r1=routeRepository.findById(routeId);
        if (r1.isEmpty()){
            throw new RouteIDNotFoundException("Route ID not Found");
        }
        return r1.get();
    }

    @Override
    public Route updateRoute(Route route) throws RouteIDNotFoundException {
        if (getRouteById(route.getRouteId())!=null){
            return routeRepository.save(route);
        }
        throw  new RouteIDNotFoundException("Route ID Not Found! Search for another Route!");
    }

    @Override
    public String deleteRouteById(int routeId) throws RouteIDNotFoundException {
        if(getRouteById(routeId)!=null){
            routeRepository.deleteById(routeId);
            return "Route Deleted Successfully for Route: "+ routeId;
        }
        throw  new RouteIDNotFoundException("Route ID Not Found! Try for another Route!");
    }
}
