package com.altimetrik.Route.service;

import com.altimetrik.Route.exception.RouteIdNotExistsException;
import com.altimetrik.Route.model.Route;
import com.altimetrik.Route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route r) {
        return routeRepository.save(r);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(int routeId) throws RouteIdNotExistsException {
        Optional<Route> pro=routeRepository.findById(routeId);
//        pro.orElseThrow(()->throw new ProductIdNot)
        if(pro.isEmpty()){
            throw new RouteIdNotExistsException("Route does not exists in the db!!check the Id");
        }
        return pro.get();
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistsException {
        if(getRouteById(route.getRouteId())!=null) {
            return routeRepository.save(route);
        }
        return null;
    }

    public String deleteRouteById(int routeId) throws RouteIdNotExistsException {
        String message="Route does not exists to delete";
        if(getRouteById(routeId)!=null) {
            routeRepository.deleteById(routeId);
            message="Route deleted successfully";
            return message;
        }
        return message;
    }
}
