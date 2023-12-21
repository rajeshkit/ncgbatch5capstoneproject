package com.railwaybooking.Route.service;

import com.railwaybooking.Route.exception.RouteIdNotFoundException;
import com.railwaybooking.Route.model.RouteInfo;
import com.railwaybooking.Route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{
    @Autowired
    private RouteRepository routeRepository;
    @Override
    public RouteInfo addRoute(RouteInfo routeInfo) {

        return routeRepository.save(routeInfo);
    }

    @Override
    public List<RouteInfo> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public RouteInfo getRouteById(Long routeId) throws RouteIdNotFoundException {
        Optional<RouteInfo> r=routeRepository.findById(routeId);
        if(r.isEmpty()){
            throw new RouteIdNotFoundException("Route id not found Exception");
        }
        return r.get();
    }

    @Override
    public RouteInfo updateRouteInfo(RouteInfo routeInfo) throws RouteIdNotFoundException {
       if(getRouteById(routeInfo.getRouteId())!=null){
           return routeRepository.save(routeInfo);
       }
        return null;
    }

    @Override
    public String deleteRouteById(Long routeId) throws RouteIdNotFoundException {
        String message="Route Id doesn't exist to delete";
        RouteInfo ri=getRouteById(routeId);
        if(ri!=null){
            routeRepository.deleteById(routeId);
            message="Route deleted Successfully";
            return message;
        }
        return message;
    }
}
