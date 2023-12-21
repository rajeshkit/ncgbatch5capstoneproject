package com.altimetrik.route.routerestapi.controller;

import com.altimetrik.route.routerestapi.exception.RouteNumberNotFoundException;
import com.altimetrik.route.routerestapi.model.Route;
import com.altimetrik.route.routerestapi.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/route-api")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    public ResponseEntity<Route> addRoute(@RequestBody Route route) {
        routeService.addRoute(route);
        //location is http header which we are passing to response-entity so user-
        //will be able to get status and http link to redirect to added data
        //ServletUriComponentBuilder is a inbuilt class in java which is used to get
        //current request and from that we are attaching id to it which we're getting
        //through getRouteId..so user will directly get http link to retrieve which data
        // got added
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(route.getRouteId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/route")
    public List<Route> getAllRoute() {

        return routeService.getAllRoute();
    }

    @GetMapping(value = "/route/{id}")
    public Route getRouteById(@PathVariable("id") String routeId) throws RouteNumberNotFoundException {

        return routeService.getRouteById(routeId);
    }

    @PutMapping(value = "/route/{id}")
    public ResponseEntity<String> updateRoute(@RequestBody Route route, @PathVariable("id") String routeId) throws RouteNumberNotFoundException {
        String msg;
        Route savedRoute = routeService.updateRoute(route, routeId);
        if (savedRoute == null) {
            msg = "Route Id " + routeId + " not Found";
            return ResponseEntity.ok(msg);
        } else {
            msg = "Route Details Updated Successfully";
            return ResponseEntity.ok(msg);
        }

    }

    @DeleteMapping(value = "/route/{id}")
    public ResponseEntity<String> deleteRouteByID(@PathVariable("id") String routeId) throws RouteNumberNotFoundException {
        String msg = routeService.deleteRouteByID(routeId);
        return ResponseEntity.ok(msg);
    }

}