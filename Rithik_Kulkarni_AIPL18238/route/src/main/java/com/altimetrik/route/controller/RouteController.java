package com.altimetrik.route.controller;

import com.altimetrik.route.exception.RouteIdNotExistException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.sevice.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route-api")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Valid Route route){
        System.out.println("Adding Route...");
        return routeService.addRoute(route);
    }

    @GetMapping(value = "/route")
    public List<Route> getAllRoutes(){
        System.out.println("Getting All Routes");
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/route/{routeId}")
    public Route getRouteById(@PathVariable("routeId") int routeId) throws RouteIdNotExistException {
        System.out.println("Getting Route By Route Id");
        return routeService.getRouteByRouteId(routeId);
    }

    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody Route route) throws RouteIdNotExistException{
        System.out.println("Updating Route.");
        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/route/{routeId}")
    public String deleteRouteById(@PathVariable("routeId") int routeId) throws RouteIdNotExistException{
        System.out.println("Deleting Route");
        return routeService.deleteRouteById(routeId);
    }

}
