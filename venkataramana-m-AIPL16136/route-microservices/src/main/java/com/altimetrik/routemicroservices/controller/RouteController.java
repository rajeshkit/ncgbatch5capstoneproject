package com.altimetrik.routemicroservices.controller;

import com.altimetrik.routemicroservices.exception.RouteIdAlreadyExistsException;
import com.altimetrik.routemicroservices.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservices.model.Route;
import com.altimetrik.routemicroservices.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/routes-api")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/routes")
    public Route addRoute(@RequestBody @Valid Route route) throws RouteIdAlreadyExistsException {
        return routeService.addRoute(route);
    }

    @GetMapping(value = "/routes")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/routes/{routeId}")
    public Route getRouteById(@PathVariable long routeId) throws RouteIdNotExistsException {
        return routeService.getRouteById(routeId);
    }

    @PutMapping("/routes")
    public Route updateRoute(@RequestBody Route route) throws RouteIdNotExistsException {
        return routeService.updateRoute(route);
    }

    @DeleteMapping("/routes/{routeId}")
    public String deleteRouteById(@PathVariable("routeId") long routeId) throws RouteIdNotExistsException {
        return routeService.deleteRouteById(routeId);
    }

}
