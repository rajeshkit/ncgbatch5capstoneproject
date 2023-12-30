package com.project.routeService.service;


import com.project.routeService.exception.RouteNotFoundException;
import com.project.routeService.model.Route;
import com.project.routeService.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImplementation implements RouteServe{

    @Autowired
    private RouteRepo routeRepository ;


    @Override
    public Route addRoute(Route route)
    {
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes()
    {
        return routeRepository.findAll();
    }


    @Override
    public Optional<Route> getRouteById(int routeId) throws RouteNotFoundException {
        Optional<Route> routeno1 = routeRepository.findById(routeId);
        if (routeno1.isEmpty()){
            //
        }
        return routeno1;

    }


    @Override
    public String deleteRoute(int routeId) throws RouteNotFoundException{

        Optional<Route> routeno1 =routeRepository.findById(routeId);

        if (routeno1.isEmpty()){
            //throw new RouteisnotExistException("Route is not exist here", "Enter the valid Id");
        }
        routeRepository.deleteById(routeId);

        return null ;

    }


    @Override
    public Route updateRoute(Route route ) throws RouteNotFoundException{

        Optional<Route> routeno1 = routeRepository.findById(route.getRouteId());

        Route routeno2 = null ;

        if (routeno1.isPresent()){
            routeno2 = routeno1.get();
        }

        routeno2.setDestination(route.getDestination());
        routeno2.setSource(route.getSource());
        routeno2.setTotalkms(route.getTotalkms());

        routeRepository.save(routeno2);

        return routeno2 ;

    }



}

