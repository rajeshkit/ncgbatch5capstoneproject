package com.altimetrik.route.controller;

import com.altimetrik.route.exception.RouteAlreadyExistsException;
import com.altimetrik.route.exception.RouteIdNotExistsException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;
    public RouteController(RouteService routeService){
        this.routeService=routeService;
    }
    @RequestMapping(value = "/route-api/route",method = RequestMethod.GET)
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }
    @RequestMapping(value = "/route-api/route/{id}",method = RequestMethod.GET)
    public Route getRouteById(@PathVariable("id") int routeId)throws RouteIdNotExistsException {
        return routeService.getRouteById(routeId);
    }
    @RequestMapping(value = "/route-api/route/",method = RequestMethod.POST)
    @ResponseBody
    public Route addRoute(@RequestBody @Valid Route route) throws RouteAlreadyExistsException {
        return routeService.addRoute(route);
    }
    @RequestMapping(value = "/route-api/route",method = RequestMethod.PUT)
    @ResponseBody
    public Route updateRoute(@RequestBody Route route) throws RouteIdNotExistsException
    {
        return routeService.updateRoute(route);
    }
    @RequestMapping(value = "/route-api/route/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteRoute(@PathVariable("id") int routeId) throws RouteIdNotExistsException
    {
        return routeService.deleteRoute(routeId);
    }
}
