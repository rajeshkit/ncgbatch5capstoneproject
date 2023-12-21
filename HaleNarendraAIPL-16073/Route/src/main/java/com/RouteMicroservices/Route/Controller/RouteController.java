package com.RouteMicroservices.Route.Controller;

import com.RouteMicroservices.Route.Exception.RouteIdNotFoundException;
import com.RouteMicroservices.Route.Services.RouteService;
import com.RouteMicroservices.Route.model.RouteResources;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route-api")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @PostMapping(value="/route")
    public RouteResources addRoute(@RequestBody @Valid RouteResources routeResources){
        System.out.println(routeResources);
        return routeService.addRouteResources(routeResources);
    }
    @GetMapping(value="/route")
    public List<RouteResources> allRouteDetail(){

        return routeService.getAllRouteDetail();
    }

    @GetMapping(value="/route/{id}")
    public RouteResources getRouteById(@PathVariable("id") Long routeId) throws RouteIdNotFoundException {

        return routeService.getRouteById(routeId);
    }

    @PutMapping(value = "/route")
    public RouteResources updateRouteDetail(@RequestBody RouteResources routeResources) throws RouteIdNotFoundException {


        return routeService.updateRouteDetail(routeResources);
    }

    @DeleteMapping(value="/route/{id}")
    public String deleteRouteById(@PathVariable("id") Long routeId) throws RouteIdNotFoundException {

        return routeService.deleteRouteById(routeId);
    }

}
