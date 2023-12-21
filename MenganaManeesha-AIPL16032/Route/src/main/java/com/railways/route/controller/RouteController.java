package com.railways.route.controller;

import com.railways.route.exception.RouteIdExistsException;
import com.railways.route.exception.RouteNotFindException;
import com.railways.route.model.Route;
import com.railways.route.service.RouteService;
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

    @PostMapping("/route")
    public Route addRoute(@RequestBody @Valid Route route) throws RouteIdExistsException {
        return routeService.addRoute(route);
    }

    @GetMapping("/route")
    public List<Route> getRoute() {
        return routeService.getRoute();
    }

    @GetMapping("/route/{id}")
    public Route getRouteById(@PathVariable("id") Long routeId) throws RouteNotFindException {
        return routeService.getRouteById(routeId);
    }

    @PutMapping("/route")
    public Route updateRoute(@RequestBody Route route) throws RouteNotFindException {
        return routeService.updateRoute(route);
    }

    @DeleteMapping("/route/{id}")
    public String deleteRoute(@PathVariable("id") Long routeId) throws RouteNotFindException {
        return routeService.deleteRoute(routeId);
    }

}
