package com.altimetrik.routemicroservice.service;


import com.altimetrik.routemicroservice.exceptions.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    Route addRoute(Route route);

    List<Route> getAllRoutes();

    Route getRouteById(String routeId) throws RouteIdNotExistsException;

    Route updateRoute(Route route) throws RouteIdNotExistsException;

    String deleteRouteById(String routeId) throws RouteIdNotExistsException;
}
