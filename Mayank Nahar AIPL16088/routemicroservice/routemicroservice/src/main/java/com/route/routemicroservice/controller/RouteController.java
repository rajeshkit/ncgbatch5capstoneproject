package com.route.routemicroservice.controller;

import com.route.routemicroservice.exception.RouteIdNotFoundException;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.service.RouteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route-api")
@Slf4j
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Valid Route route) {
        Route r1 = routeService.addRoute(route);
        log.info("Request sent and Route Added");
        return r1;
    }

    @GetMapping(value = "/route")
    public List<Route> getAllRoute() {
        log.info("Request sent to service for get all route details");
        return routeService.getAllRoute();
    }

    @GetMapping(value = "/route/{id}")
    public Route getRouteById(@PathVariable("id") int routeId) throws RouteIdNotFoundException {
        log.info("Request sent to service for get route by id");

        return routeService.getRouteById(routeId);
    }

    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody Route route) throws RouteIdNotFoundException {
        log.info("Request sent to service for update route details");

        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/route/{id}")
    public String deleteRouteById(@PathVariable("id") int routeId) throws RouteIdNotFoundException {
        log.info("Request sent to service");
        return routeService.deleteRouteById(routeId);
    }


}
