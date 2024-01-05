package com.trainbooking.routemicroservices.controller;

import com.trainbooking.routemicroservices.exception.RouteIdNotExistException;
import com.trainbooking.routemicroservices.model.Route;
import com.trainbooking.routemicroservices.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route-api")
public class RouteController {

    @Autowired
    private RouteService routeService;
           //Dependency Inversion Principle: Dependency should be on abstraction not on implementation.

    //To create Route resource
    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Valid Route route){
        return routeService.addRoute(route);
    }

    //To get all routes
    @GetMapping(value = "/route")
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    //To get route by routeId
    @GetMapping(value = "/route/{routeId}")
    public Route getRouteByRouteId(@PathVariable("routeId") int routeId) throws RouteIdNotExistException {
        return routeService.getRouteByRouteId(routeId);
    }

    //To update route details
    @PutMapping(value = "/route")
    public Route updateRouteDetails(@RequestBody @Valid Route route) throws RouteIdNotExistException {
        return routeService.updateRouteDetails(route);
    }

    //To delete route by routeId

    @DeleteMapping(value = "/route/{routeId}")
    public String deleteRouteByRouteId(@PathVariable("routeId") int routeId) throws RouteIdNotExistException {
        return routeService.deleteRouteByRouteId(routeId);
    }



}
