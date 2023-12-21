package com.altimetrik.Route.controller;

import com.altimetrik.Route.exception.RouteIdNotExistsException;
import com.altimetrik.Route.model.Route;
import com.altimetrik.Route.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/route-api")
public class RouteController {
    private static final Logger logger = Logger.getLogger(RouteController.class.getName());

    @Autowired
    private RouteService routeService;
    @PostMapping(value="/route")
    public Route addRoute(@RequestBody @Valid Route route){
        logger.info("Adding a new route: " + route.getRouteId());

        return routeService.addRoute(route);
    }
    @GetMapping(value="/route")
    public List<Route> getAllRoutes(){
        logger.info("Fetching all routes");
        return routeService.getAllRoutes();
    }
    @GetMapping(value="/route/{id}")
    public Route getRouteById(@PathVariable("id") int routeId) throws RouteIdNotExistsException {
        logger.info("Fetching route by ID: " + routeId);
        return routeService.getRouteById(routeId);
    }
    @PutMapping(value="/route")
//    @ResponseBody
    public Route updateRoute(@RequestBody Route route) throws RouteIdNotExistsException {
        logger.info("Updating route with ID: " + route.getRouteId());
        return routeService.updateRoute(route);
    }
    @DeleteMapping(value="/route/{id}")
//    @ResponseBody
    public String deleteRouteById(@PathVariable("id") int routeId) throws RouteIdNotExistsException {
        logger.info("Deleting route with ID: " + routeId);

        return routeService.deleteRouteById(routeId);
    }
}
