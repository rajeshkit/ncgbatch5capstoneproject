package com.altimetrik.routemicroservice.controller;

import com.altimetrik.routemicroservice.exceptions.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping
    public ResponseEntity<?> addRoute(@Valid @RequestBody Route route, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, returning the error messages
            List<FieldError> errors = result.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        // Saving the route if validation passes
        Route addedRoute = routeService.addRoute(route);

        // Returning the created route with a 201 Created status
        return new ResponseEntity<>(addedRoute, HttpStatus.CREATED);
    }

    /*
    -------------FORMAT-------------
    {
      "routeId":"R0004",
      "source": "Chennai",
      "destination": "Bengaluru",
      "totalKms": 400
    }
    -------------FORMAT-------------
    */


    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();

        if (routes.isEmpty()) {
            // Returning 404 Not Found if no routes are found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the list of routes with 200 OK status
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getRouteById(@PathVariable("id") String routeId) {
        try {
            // Retrieving the route if it exists
            Route route = routeService.getRouteById(routeId);

            // Returning the route with a 200 OK status
            return new ResponseEntity<>(route, HttpStatus.OK);
        } catch (RouteIdNotExistsException e) {
            // Handling the case where the routeId does not exist
            return new ResponseEntity<>("Route with ID " + routeId + " not found.", HttpStatus.NOT_FOUND);
        }
    }





    @PutMapping
    public ResponseEntity<?> updateRoute(@RequestBody @Valid Route route, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, returning the error messages
            List<FieldError> errors = result.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            // Updating the route if it exists
            Route updatedRoute = routeService.updateRoute(route);

            // Returning the updated route with a 200 OK status
            return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
        } catch (RouteIdNotExistsException e) {
            // Handling if the routeId does not exist
            return new ResponseEntity<>("Cannot update. Route with routeId " + route.getRouteId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable("id") String routeId) {
        try {
            // Deleting the route if it exists
            routeService.deleteRouteById(routeId);
            // Returning a success message with a 200 OK status
            return new ResponseEntity<>("Route with ID " + routeId + " deleted successfully.", HttpStatus.OK);
        } catch (RouteIdNotExistsException e) {
            // Handling the case where the routeId does not exist
            return new ResponseEntity<>("Cannot delete. Route with ID " + routeId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

}
