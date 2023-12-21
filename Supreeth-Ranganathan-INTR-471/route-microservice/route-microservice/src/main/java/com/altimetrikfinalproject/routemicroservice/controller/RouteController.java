package com.altimetrikfinalproject.routemicroservice.controller;

import com.altimetrikfinalproject.routemicroservice.exception.RouteDoesNotExistException;
import com.altimetrikfinalproject.routemicroservice.model.Route;
import com.altimetrikfinalproject.routemicroservice.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/route-api")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Valid Route route){
        return routeService.addRoute(route);
    }
    @GetMapping(value="/route")
    public List<Route> getAllRoutes(){
        return routeService.getAllRoute();
    }
    @GetMapping(value="/route/{routeId}")
    public Optional<Route> getRouteByID(@PathVariable int routeId) throws RouteDoesNotExistException {
        return routeService.getRouteById(routeId);
    }
    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody @Valid Route route) throws RouteDoesNotExistException {
        return routeService.updateRoute(route);
    }
    @DeleteMapping(value = "/route/{routeId}")
    public String deleteRoute(@PathVariable int routeId) throws RouteDoesNotExistException {
        return routeService.deleteRoute(routeId);
    }
}
