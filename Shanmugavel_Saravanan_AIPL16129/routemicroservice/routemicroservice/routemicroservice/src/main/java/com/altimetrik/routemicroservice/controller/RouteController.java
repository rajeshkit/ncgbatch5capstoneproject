package com.altimetrik.routemicroservice.controller;


import com.altimetrik.routemicroservice.exception.RouteIdAlreadyExists;
import com.altimetrik.routemicroservice.exception.RouteNotFoundException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/route-api")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    public ResponseEntity<Route> addRoute(@RequestBody @Valid Route route) throws RouteIdAlreadyExists {
        Route addedRoute = routeService.addRoute(route);
        return new ResponseEntity<>(addedRoute, HttpStatus.CREATED);
    }

    @GetMapping(value = "/route")
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(value = "/route/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable("id") int routeId) {
        try {
            Route route = routeService.getRouteById(routeId);
            return new ResponseEntity<>(route, HttpStatus.OK);
        } catch (RouteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/route")
    public ResponseEntity<Route> updateRoute(@RequestBody @Valid Route route) {
        try {
            Route updatedRoute = routeService.updateRoute(route);
            if (updatedRoute != null) {
                return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RouteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/route/{id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable("id") int routeId) {
        try {
            String message = routeService.deleteRouteById(routeId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (RouteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
