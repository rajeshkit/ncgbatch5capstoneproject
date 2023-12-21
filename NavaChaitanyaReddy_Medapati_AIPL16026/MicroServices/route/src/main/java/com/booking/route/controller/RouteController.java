package com.booking.route.controller;

import com.booking.route.exception.RouteIdNotExistsException;
import com.booking.route.model.RouteResources;
import com.booking.route.service.RouteService;
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

    @PostMapping(value = "/route")
    public RouteResources addRouteResources(@RequestBody @Valid RouteResources routeResources)
    {
        return routeService.addRouteResources(routeResources);
    }

    @GetMapping(value = "/route")
    public List<RouteResources> getAllRouteResources()
    {
        return routeService.getAllRouteResources();
    }

    @GetMapping(value = "/route/{id}")
    public RouteResources getRouteResourcesById(@PathVariable("id") Long routeId) throws RouteIdNotExistsException {
        return routeService.getAllRouteResourcesById(routeId);
    }

    @PutMapping(value = "/route")
    public RouteResources updateRouteResource(@RequestBody RouteResources routeResources) throws RouteIdNotExistsException {
        return routeService.updateRouteResource(routeResources);
    }

    @DeleteMapping(value = "/route/{id}")
    public String deleteRouteResourceById(@PathVariable("id") Long trainNumber) throws RouteIdNotExistsException {
        return routeService.deleteRouteResourceById(trainNumber);
    }
}
