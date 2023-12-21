package com.routealtimetrik.routemicroservice.controller;


import com.routealtimetrik.routemicroservice.exception.RouteIdAlreadyExistException;
import com.routealtimetrik.routemicroservice.exception.RouteIdNotExistException;
import com.routealtimetrik.routemicroservice.model.Route;
import com.routealtimetrik.routemicroservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/route-api")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping(value="/route")
    public Route addRoute(@RequestBody @Valid Route route) throws RouteIdNotExistException, RouteIdAlreadyExistException {
        return routeService.addRoute(route);
    }

    @GetMapping(value = "/route")
    public List<Route> getAllRoutes() throws RouteIdNotExistException {
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/route/{id}")
    public Route getRouteById(@PathVariable("id") int routeId) throws RouteIdNotExistException {
        return routeService.getRouteById(routeId);
    }

    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody Route route) throws RouteIdNotExistException {
        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/route/{id}")
    public String deleteRouteById(@PathVariable("id") int routeId) throws RouteIdNotExistException {
        return routeService.deleteRouteById(routeId);
    }

}
