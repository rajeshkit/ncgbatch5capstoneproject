package com.railwaybooking.Route.controller;

import com.railwaybooking.Route.exception.RouteIdNotFoundException;
import com.railwaybooking.Route.model.RouteInfo;
import com.railwaybooking.Route.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routeInfo-api")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @PostMapping(value="/routeInfo")
    public RouteInfo addRoute(@RequestBody @Valid RouteInfo routeInfo){

        return routeService.addRoute(routeInfo);
    }

    @GetMapping(value="/routeInfo")
    public List<RouteInfo> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/routeInfo/{id}")
    public RouteInfo getRouteById(@PathVariable("id") Long routeId) throws RouteIdNotFoundException{
        return routeService.getRouteById(routeId);
    }

    @PutMapping(value="/routeInfo")
    public RouteInfo updateRouteInfo(@RequestBody RouteInfo routeInfo) throws RouteIdNotFoundException{
        return routeService.updateRouteInfo(routeInfo);
    }

    @DeleteMapping(value = "/routeInfo/{id}")
    public String deleteRouteById(@PathVariable("id") Long routeId) throws RouteIdNotFoundException{
        return routeService.deleteRouteById(routeId);
    }


}
