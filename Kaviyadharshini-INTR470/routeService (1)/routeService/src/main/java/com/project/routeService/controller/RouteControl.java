package com.project.routeService.controller;


import com.project.routeService.exception.RouteNotFoundException;
import com.project.routeService.model.Route;
import com.project.routeService.service.RouteServe;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/route-restapi")

public class RouteControl {

    @Autowired
    private RouteServe routeService;


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
    public Optional<Route> getRouteById(@PathVariable int routeId) throws RouteNotFoundException
    {
        return routeService.getRouteById(routeId);
    }

    @DeleteMapping(value="/route/{routeId}")
    public String deleteRoute(@PathVariable int routeId ) throws RouteNotFoundException
    {
        return routeService.deleteRoute(routeId);
    }

    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody @Valid Route route ) throws RouteNotFoundException
    {
        return routeService.updateRoute(route);
    }



}

