package com.rajaparvathi.route.controller;

import com.rajaparvathi.route.model.Route;
import com.rajaparvathi.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route-api")

public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    private Route addRouteDetails(@RequestBody Route route){
        return routeService.addRouteDetails(route);

    }
    @GetMapping(value = "/route")
    private List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }
    @GetMapping(value = "/route/{id}")
    private Route SearchTrainByNumber(@PathVariable("id") int routeId){
        return routeService.searchRouteById(routeId);
    }
    @PutMapping(value = "/route")
    public Route updateDetails(@RequestBody  Route route){
        return routeService.updateDetails(route);
    }
    @DeleteMapping(value = "/route/{id}")
    public String removeTrainByNumber(@PathVariable("id") int routeId){
        return routeService.removeRouteById(routeId);
    }
}
