package com.altimetrik.route.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.altimetrik.route.exception.RouteIdDoNotExitsException;
import com.altimetrik.route.service.RouteService;
import com.altimetrik.route.model.Route;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/route")
public class RouteContoller {
    @Autowired
    private RouteService routeService;

    @GetMapping("/{routeId}")
    public Route getRouteById(@PathVariable int routeId) throws RouteIdDoNotExitsException {
        return routeService.getRouteById(routeId);
    }


    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Valid Route route) {
        return routeService.addRoute(route);
    }

    @GetMapping(value = "/route")
    public List<Route> getAllRoute() {
        return routeService.getAllRoutes();
    }

    @PutMapping(value = "/route")
    public Route updateRoutes(@RequestBody Route route) throws RouteIdDoNotExitsException {
        return routeService.updateRoute(route);
    }
    @DeleteMapping(value = "/route/{id}")
    public String deleteRouteById(@PathVariable("id") int routeId) throws RouteIdDoNotExitsException {
        return routeService.deleteRouteById(routeId);
    }
}
