package com.booking_details.route.controller;

import com.booking_details.route.exception.RouteIdNotFoundException;
import com.booking_details.route.model.RouteModel;
import com.booking_details.route.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/route-microservice")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @PostMapping(value = "/route")
    public RouteModel addRouteDetails(@RequestBody @Valid RouteModel routeModel)
    {
        return routeService.addRouteDetails(routeModel);
    }

    @GetMapping(value = "/route")
    public List<RouteModel> getAllRouteDetails()
    {
        return routeService.getAllRouteDetails();
    }

    @GetMapping(value = "/route/{id}")
    public RouteModel getAllRouteDetailsById(@PathVariable("id") Long routeId) throws RouteIdNotFoundException {
        return routeService.getAllRouteDetailsById(routeId);
    }

    @PutMapping(value = "/route")
    public RouteModel updateRouteDetails(@RequestBody RouteModel routeModel) throws RouteIdNotFoundException {
        return routeService.updateRouteDetails(routeModel);
    }

    @DeleteMapping(value = "/route/{id}")
    public String deleteRouteDetailsById(@PathVariable("id") Long trainNumber) throws RouteIdNotFoundException {
        return routeService.deleteRouteDetailsById(trainNumber);
    }
}
