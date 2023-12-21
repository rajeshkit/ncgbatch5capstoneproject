package com.routemicroservice.controller;

import com.routemicroservice.exception.RouteIdDoesNotExistException;
import com.routemicroservice.model.Route;
import com.routemicroservice.service.RouteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/route-api")
public class RouteController {

    @Autowired
    private RouteService routeService;
    private final Logger logger = LoggerFactory.getLogger(RouteController.class);
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/route")
    public Route saveRouteDetails(@RequestBody @Valid Route route) {
        logger.info("Received a request to add a route : {}", route);
        return routeService.saveRouteDetails(route);
    }

    @GetMapping("/route")
    public List<Route> getAllRoutesDetail() {
        logger.info("Received a request to get all the routes detail.");
        return routeService.getAllRoutesDetail();
    }

    @GetMapping("/{id}")
    public Route getRouteDetailsById(@PathVariable("id") int routeId) throws RouteIdDoesNotExistException {
        logger.info("Received a request to get route details with ID: {}", routeId);
        return routeService.getRouteDetailsById(routeId);
    }

    @PutMapping("/route")
    public Route updateRouteDetails(@RequestBody  Route route) throws RouteIdDoesNotExistException {
        logger.info("Received a request to update route : {}", route);
        return routeService.updateRouteDetails(route);
    }

    @DeleteMapping("/{id}")
    public String deleteRouteDetails(@PathVariable("id") int routeId) throws RouteIdDoesNotExistException {
        logger.info("Received a request to delete route with ID: {}", routeId);
        return routeService.deleteRouteDetails(routeId);
    }
}