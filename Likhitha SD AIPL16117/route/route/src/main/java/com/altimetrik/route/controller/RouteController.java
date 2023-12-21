package com.altimetrik.route.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.altimetrik.route.exception.RouteNotExistsException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.service.RouteService;


@RestController
@RequestMapping("/route-api")

public class RouteController {
    @Autowired
    private RouteService routeService;
    @PostMapping(value = "/route")
    public Route addRoute(@RequestBody @Validated Route route) {// throw new MethodInvalidArgException();
        return routeService.addRoute(route);
    }
    @GetMapping(value = "/route")
    public List<Route> getAllroute() {
        return routeService.getAllroute();
    }
    @GetMapping(value = "/route/{no}")
    public Route getRouteById(@PathVariable("no") int routeId) throws RouteNotExistsException {
        return routeService.getRouteById(routeId);
    }
    @PutMapping(value = "/route")
    public Route updateRoute(@RequestBody  Route route) throws RouteNotExistsException {
        return routeService.updateRoute(route);
    }
    @DeleteMapping(value = "/route/{no}")
    public String deleteRouteById(@PathVariable("no") int routeId) throws RouteNotExistsException {
        return routeService.deleteRouteById(routeId);
    }


}