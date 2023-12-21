package com.altimetrik.route.controller;

import com.altimetrik.route.entity.Route;
import com.altimetrik.route.exception.RouteNotExistsException;
import com.altimetrik.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {
    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<Route> addRoute(@RequestBody @Valid Route route) {
        return new ResponseEntity<>(routeService.addRoute(route), HttpStatus.CREATED);
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<Route> getRouteById(@PathVariable("routeId") int routeId) {
        return new ResponseEntity<>(routeService.getRouteById(routeId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoute() {
        return new ResponseEntity<>(routeService.getAllRoute(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Route> updateRoute(@RequestBody Route route) throws RouteNotExistsException {
        return new ResponseEntity<>(routeService.updateRoute(route), HttpStatus.CREATED);
    }

    @DeleteMapping("/{routeId}")
    public ResponseEntity<String> deleteRoute(@PathVariable("routeId") int routeId) throws RouteNotExistsException {
        return new ResponseEntity<>(routeService.deleteRoute(routeId), HttpStatus.OK);
    }

}
