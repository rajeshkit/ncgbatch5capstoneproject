package com.finalproject.routemicroservice.controller;


import com.finalproject.routemicroservice.exception.RouteIsNotExistException;
import com.finalproject.routemicroservice.model.Route;
import com.finalproject.routemicroservice.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/route-restapi")

public class RouteController {

    @Autowired
    private RouteService routeService;


    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Valid Route route)
    {
        return routeService.addRoute(route);
    }

    @GetMapping(value = "/route")
    public List<Route> getAllRoutes()
    {
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/route/{routeId}")
    public Optional<Route> getRouteById(@PathVariable int routeId) throws RouteIsNotExistException
    {
        return routeService.getRouteById(routeId);
    }

    @DeleteMapping(value="/route/{routeId}")
    public String deleteRoute(@PathVariable int routeId ) throws RouteIsNotExistException
    {
        return routeService.deleteRoute(routeId);
    }

    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody @Valid Route route ) throws RouteIsNotExistException
    {
        return routeService.updateRoute(route);
    }



}
