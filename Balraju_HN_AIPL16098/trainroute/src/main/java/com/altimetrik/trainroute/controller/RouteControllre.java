package com.altimetrik.trainroute.controller;
import com.altimetrik.trainroute.exception.NoSuchElementException;
import jakarta.validation.Valid;
import com.altimetrik.trainroute.modle.Route;
import com.altimetrik.trainroute.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.NoSuchElementException;


@RestController
@RequestMapping("/route-api")
public class RouteControllre {


        @Autowired
        private RouteService routeService;
        @PostMapping(value = "/route")
        public Route addRoute(@RequestBody @Valid Route route) {// throw new MethodInvalidArgException();
            return routeService.addRoute(route);
        }
        @GetMapping(value = "/route")
        public List<Route> getAllRoute() {
            return routeService.getAllRoute();
        }
        @GetMapping(value = "/route/{id}")
        public Route getRouteById(@PathVariable("id") int routeId) throws NoSuchElementException {
            return routeService.getRouteById(routeId);
        }
        @PutMapping(value = "/route")
        public Route updateRoute(@RequestBody  Route route)throws NoSuchElementException  {
            return routeService.updateRoute(route);
        }
        @DeleteMapping(value = "/route/{id}")
        public String deleteRouteById(@PathVariable("id") int RouteId) throws NoSuchElementException {
            return routeService.deleteRouteById(RouteId);
        }

}
