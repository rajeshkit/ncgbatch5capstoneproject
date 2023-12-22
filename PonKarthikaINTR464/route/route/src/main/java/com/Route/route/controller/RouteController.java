package com.Route.route.controller;

import com.Route.route.exception.RouteIdNotFoundException;
import com.Route.route.model.Route;
import com.Route.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/route-api")
public class RouteController {
    private RouteService routeService;
    @Autowired
    public RouteController(RouteService routeService) {

        this.routeService = routeService;
    }
    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody Route route) {

        return routeService.addRoute(route);
    }
    @GetMapping(value = "/route")
    public List<Route> getAllRoutes() {

        return routeService.getAllRoutes();
    }
    @GetMapping(value = "/route/{Id}")
    public Optional<Route> getRouteById(@PathVariable("Id") int routeId){

        return routeService.getRouteById(routeId);
    }
    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody  Route route)  {

        return routeService.updateRoute(route);
    }
    @DeleteMapping(value = "/route/{Id}")
    public String deleteRouteById(@PathVariable("Id") int routeId) throws RouteIdNotFoundException {

        return routeService.deleteRouteById(routeId);
    }
}
