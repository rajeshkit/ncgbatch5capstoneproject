package com.altimetrik.trainroute.service;

import com.altimetrik.trainroute.exception.NoSuchElementException;
import com.altimetrik.trainroute.modle.Route;
import com.altimetrik.trainroute.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImp implements RouteService{
    @Autowired
    private RouteRepository routeRepositry;

    @Override
    public Route addRoute(Route route) {
        return routeRepositry.save(route);
    }

    @Override
    public List<Route> getAllRoute() {
        return routeRepositry.findAll();
    }

    @Override
    public Route getRouteById(int routeId) throws NoSuchElementException {
        Optional<Route> pro=routeRepositry.findById(routeId);
        if(pro.isEmpty()){
            throw new NoSuchElementException("Product is not exists in the db!!! check the product ID");//NoSuchElementException
        }
        return pro.get();
    }

    @Override
    public Route updateRoute(Route route) throws NoSuchElementException{
        if(getRouteById(route.getRouteId())!=null) {
            return routeRepositry.save(route);
        }
        return null;

    }

    @Override
    public String deleteRouteById(int routeId) throws NoSuchElementException{
        String message="Product Does not exists to delete";
        Route r=getRouteById(routeId);
        if(r!=null){
            routeRepositry.deleteById(routeId);
            message="Product deleted successfully";
            return message;
        }
        return message;
    }
}

