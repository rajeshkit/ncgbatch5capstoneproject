package com.route.routemicroservice.controller;

import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody Route route){
        return routeService.addRoute(route);
    }

    @GetMapping(value = "/route")
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/route/{routeId}")
    public Route getRouteByRouteId(@PathVariable("routeId") int routeId){
        return routeService.getRouteByRouteId(routeId);
    }

    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody Route route){
        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/route/{routeId}")
    public void deleteRouteByRouteId(@PathVariable("routeId") int routeId){
        routeService.deleteRouteByRouteId(routeId);
    }
}
