package com.altimetrik.route.sevice;

import com.altimetrik.route.exception.RouteIdNotExistException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.repository.RouteRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Log
@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        System.out.println("Route Added Successfully..");
        log.info("Inserting Route docs with routeId: "+route.getRouteId());
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        System.out.println("Getting all routes...");
        log.info("Fetching all route docs.");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteByRouteId(int routeId) throws RouteIdNotExistException {
        Optional<Route> rt = routeRepository.findById(routeId);
        if(rt.isPresent()){
            System.out.println("Getting route with routeId");
            log.info("Fetching route docs with routeId: "+routeId);
            return rt.get();
        }
        else {
            throw new RouteIdNotExistException("Route Id Not Exist");
        }
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistException {
        if(getRouteByRouteId(route.getRouteId())!=null){
            System.out.println("Route Details Updated Successfully");
            log.info("Updating route with routeId: "+route.getRouteId());
            return routeRepository.save(route);
        }
        else {
            throw new RouteIdNotExistException("Route Id Not Exist");
        }
    }

    @Override
    public String deleteRouteById(int routeId) throws RouteIdNotExistException {
        Route rt =getRouteByRouteId(routeId);
        if(rt!=null){
            log.info("Deleting route with routeId: "+routeId);
            routeRepository.deleteById(routeId);
            return "Route Deleted Successfully.";
        }
        else {
            throw new RouteIdNotExistException("Route Id Not Exist");
        }
    }
}
