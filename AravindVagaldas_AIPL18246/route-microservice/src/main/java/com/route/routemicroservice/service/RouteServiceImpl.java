package com.route.routemicroservice.service;

import com.route.routemicroservice.exception.RouteIdAlreadyExistException;
import com.route.routemicroservice.exception.RouteIdNotExistException;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{

    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route addRoute(Route route) {
        List<Route> routeList=routeRepository.findAll();
        if(routeList.contains(route)){
            throw new RouteIdAlreadyExistException("RouteId Already Exist");
        }
       return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteByRouteId(int routeId) {
        Optional<Route> route=routeRepository.findById(routeId);
        if(route.isEmpty()){
            throw new RouteIdNotExistException("RouteId not Exist");
        }
        return route.get();
    }

    @Override
    public Route updateRoute(Route route) {
        if(getRouteByRouteId(route.getRouteId())!=null){
            return routeRepository.save(route);
        }
        else{
            throw new RouteIdNotExistException("RouteId Not Exist");
        }

    }

    @Override
    public String deleteRouteByRouteId(int routeId) {
        Route route=getRouteByRouteId(routeId);
        if(route==null){
            throw new RouteIdNotExistException("RouteId not Exist");
        }
        routeRepository.deleteById(routeId);
        return "Route deleted successfully";
    }
}
