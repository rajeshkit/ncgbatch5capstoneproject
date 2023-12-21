package com.Route.RouteMicroservice.controller;


import com.Route.RouteMicroservice.entity.Route;
import com.Route.RouteMicroservice.service.RouteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("route")
@Slf4j
public class RouteController {

  //  @Autowired
    RouteService routeService;
    RouteController(RouteService routeService){
        this.routeService=routeService;
    }

    @PostMapping("/addRoute")
    public Route addRoute(@Valid  @RequestBody Route route){

        log.info("adding the route object");
        return routeService.addRoute(route);
    }


    @GetMapping("/findRoute")
    public Route findRoute(@RequestParam int routeId){
        log.info("entered into findRoute");
       return routeService.findRoute(routeId);
    }
}
