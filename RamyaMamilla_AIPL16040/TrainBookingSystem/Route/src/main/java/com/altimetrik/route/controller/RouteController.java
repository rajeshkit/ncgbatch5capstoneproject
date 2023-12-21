package com.altimetrik.route.controller;

import com.altimetrik.route.exception.RouteIDNotFoundException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/route-api")
public class RouteController {

    private RouteService routeService;
    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Valid Route route){
        return routeService.addRoute(route);
    }
    @GetMapping(value = "/route")
    public List<Route> viewAllRoutes(){
        return routeService.viewAllRoutes();
    }
    @GetMapping(value = "/route/{id}")
    public Route getRouteById(@PathVariable("id") @Valid int routeId) throws RouteIDNotFoundException{
        return routeService.getRouteById(routeId);
    }
    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody @Valid Route route) throws RouteIDNotFoundException{
        return routeService.updateRoute(route);
    }
    @DeleteMapping(value = "/route/{id}")
    public String deleteRouteById(@PathVariable("id") @Valid int routeId) throws RouteIDNotFoundException{
        return routeService.deleteRouteById(routeId);
    }
}
