package com.altimetrik.routemicroservice.controller;
/*
>> This is the Controller class we can write methods here or the REST API End points
>> @Controller: It is used organize and manage request-handling components in a Spring MVC application
>> @RestController : We can use this Annotation so that we don't need to write @ResponseBody again & again
>> @RequestMapping :  We can use this outside the class and add common path of API into it so that we don't need to write it again & again
>> @Autowired :
>> @PathVariable : for passing dynamic we need to pas the value in path variable.
 */

import com.altimetrik.routemicroservice.exception.RouteIdNotExistException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/route-api")

public class RouteController
{
    @Autowired
    private RouteService routeService;
    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);
    /*
       <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Add route Details>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    @PostMapping(value = "/route")
    public Route addRouteDetail(@RequestBody @Valid Route route)
    {
//        System.out.println("Route Details Successfully Added !!");
        logger.info("Received a request to save Route Detail : {}", route);
        return routeService.addRouteDetail(route);
    }
    /*
      <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Get RouteDetail by Id>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    @GetMapping(value = "/route/{id}")
    public Route getRouteById(@PathVariable("id") int routeId) throws RouteIdNotExistException
    {
        Route route = routeService.getRouteById(routeId);
        logger.info("Received a request to get Route Detail by Id : {}", routeId);
        return route;
    }
    /*
      <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Update RouteDetails by Id>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
   */
    @PutMapping(value = "/route")
    public Route updateRouteById(@RequestBody Route route) throws RouteIdNotExistException
    {
        logger.info("Received a request to update Route Detail by Id : {}", route);
        return routeService.updateRouteById(route);
    }
    /*
       <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Delete Route by Id>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    @DeleteMapping(value = "/route/{id}")
    public String deleteRouteById(@PathVariable("id") int routeId) throws RouteIdNotExistException
    {
        logger.info("Received a request to Delete Route Detail by Id : {}", routeId);
        return routeService.deleteRouteById(routeId);
    }
    /*
       <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Get AllRoute Details>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    @GetMapping(value = "/route")
    public List<Route> getAllRouteDetails()
    {
        logger.info("Received a request to get All Route Details : {}");
        return routeService.getAllRouteDetails();
    }

}// Last Bracket
