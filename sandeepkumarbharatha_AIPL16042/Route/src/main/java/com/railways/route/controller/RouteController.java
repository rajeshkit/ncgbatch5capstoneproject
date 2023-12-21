package com.railways.route.controller;

import com.railways.route.exceptions.RouteIdNotFound;
import com.railways.route.model.Route;
import com.railways.route.services.RouteServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/route-api")
public class RouteController {
    @Autowired
    private RouteServices routeServices;

    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Valid Route route) {
        return routeServices.addRouteDeatils(route);
    }

    @GetMapping(value = "/route/{id}")
    public Route getRouteById(@PathVariable("id") long routeIdToSearch) throws RouteIdNotFound {
        return routeServices.getRouteById(routeIdToSearch);
    }

    @GetMapping(value = "/route")
    public List<Route> getAllRoutes() {
        return routeServices.getAllRoutes();
    }

    @PutMapping(value = "/route")
    public Route updateByRouteId(@RequestBody @Valid Route route) throws RouteIdNotFound{
        return routeServices.updateByRouteId(route);

    }

    @DeleteMapping("/route/{id}")
    public String deleteByRouteId(@PathVariable("id") long routeIdToDelete) throws RouteIdNotFound{
        return routeServices.deleteByRouteId(routeIdToDelete);
    }

}