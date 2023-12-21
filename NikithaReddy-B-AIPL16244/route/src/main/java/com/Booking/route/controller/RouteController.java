package com.Booking.route.controller;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.route.model.RouteResources;
import com.Booking.route.service.RouteService;
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

    @PostMapping(value="/Route_Table")
    public RouteResources addRoute(@RequestBody @Valid RouteResources routeResources){
        System.out.println(routeResources);
        return routeService.addRoute(routeResources);
    }
    @GetMapping("/Route_Table")
    public List<RouteResources> getRoute()
    {
        return routeService.getRoute();
    }
    @GetMapping("/Route_Table/{id}")
    public RouteResources getRouteById(@PathVariable("id") Long routeId) throws RouteNotFindException {
        return routeService.getRouteById(routeId);
    }
    @PutMapping("/Route_Table")
    public RouteResources updateRoute(@RequestBody RouteResources routeResources) throws RouteNotFindException {
        return routeService.updateRoute(routeResources);
    }
    @DeleteMapping("/Route_Table/{id}")
    public String deleteRoute(@PathVariable("id") Long routeId) throws RouteNotFindException {
        return routeService.deleteRoute(routeId);
    }

}

