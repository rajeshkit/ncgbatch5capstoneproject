package com.route.routemicroservice.controller;

import com.route.routemicroservice.exception.RouteNotFoundException;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    public ResponseEntity<Route> addRoute(@Valid @RequestBody Route route){
        Route createdRoute=routeService.addRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    @GetMapping(value = "/route")
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/route/{routeId}")
    public Route getRouteByRouteId(@PathVariable int routeId){
        return routeService.getRouteByRouteId(routeId);
    }

    @PutMapping(value = "/route")
    public ResponseEntity<Route>updateRoute(@Valid @RequestBody Route route){
        Route updatedRoute=routeService.updateRoute(route);
        return  ResponseEntity.ok(updatedRoute);
    }

    @DeleteMapping(value = "/route/{routeId}")
    public ResponseEntity<Void> deleteRouteByRouteId(@PathVariable int routeId){
        routeService.deleteRouteByRouteId(routeId);
        return ResponseEntity.noContent().build();
    }
}
