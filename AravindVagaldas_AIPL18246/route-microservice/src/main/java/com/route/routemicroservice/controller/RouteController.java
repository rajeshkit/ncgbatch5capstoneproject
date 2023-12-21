package com.route.routemicroservice.controller;

import com.route.routemicroservice.exception.RouteIdNotExistException;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/route/{routeId}")
    public Route getRouteByRouteId(@PathVariable("routeId") int routeId) throws RouteIdNotExistException {
        return routeService.getRouteByRouteId(routeId);
    }

    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody @Valid Route route){
        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/route/{routeId}")
    public String deleteRouteByRouteId(@PathVariable("routeId") int routeId) throws RouteIdNotExistException{
        return routeService.deleteRouteByRouteId(routeId);
    }
}
