package com.altimetrik.routemicroservice.controller;

import com.altimetrik.routemicroservice.exception.RouteIdAlreadyExistsException;
import com.altimetrik.routemicroservice.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/route-api")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Valid Route route) throws RouteIdNotExistsException, RouteIdAlreadyExistsException {
        return routeService.addRoute(route);
    }
    @GetMapping(value = "/route")
    public List<Route> getAllRoute() {
        return routeService.getAllRoute();
    }

    @GetMapping(value = "/route/{id}")
    public Route getRouteById(@PathVariable("id") int routeId) throws RouteIdNotExistsException{
        return routeService.getRouteById(routeId);
    }

    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody @Valid Route route) throws RouteIdNotExistsException{
        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/route/{id}")
    public String deleteRouteById(@PathVariable("id") int routeId) throws RouteIdNotExistsException{
        return routeService.deleteRouteById(routeId);
    }
}