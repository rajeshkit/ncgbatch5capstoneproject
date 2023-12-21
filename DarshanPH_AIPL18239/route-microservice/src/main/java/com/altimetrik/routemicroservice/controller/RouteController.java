package com.altimetrik.routemicroservice.controller;

import com.altimetrik.routemicroservice.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/route-api")
public class RouteController {
    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);
    @Autowired
    private RouteService routeService;
    @PostMapping(value ="/route")
    public Route addRoute(@RequestBody @Valid Route route){
        logger.info("Received request to add route: {}", route);
        return routeService.addRoute(route);
    }
    @GetMapping(value ="/route")
    public List<Route> getAllRoute(Route route){
        logger.info("Received request to get all routes");
        return routeService.getAllRoute();
    }
    @GetMapping(value ="/route/{id}")
    public Route getRouteById(@PathVariable("id") int routeId) throws RouteIdNotExistsException {
        logger.info("Received request to get routeById: {}", routeId);
        return routeService.getRouteById(routeId);
    }
    @PutMapping(value ="/route")
    public Route updateRoute(Route route)throws RouteIdNotExistsException{
        logger.info("Received request to update route: {}", route);
        return routeService.updateRoute(route);
    }
    @DeleteMapping(value ="/route/{id}")
    public String  deleteRouteById(@PathVariable("id") int routeId)throws RouteIdNotExistsException{
        logger.info("Received request to delete route by ID: {}", routeId);
        return routeService.deleteRouteById(routeId);
    }
}
