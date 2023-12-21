package com.altimetrik.routemicroservices.service;

import com.altimetrik.routemicroservices.exception.RouteIdAlreadyExistsException;
import com.altimetrik.routemicroservices.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservices.model.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route) throws RouteIdAlreadyExistsException;

    List<Route> getAllRoutes();

    Route getRouteById(long routeId) throws RouteIdNotExistsException;

    Route updateRoute(Route route) throws RouteIdNotExistsException;

    String deleteRouteById(long routeId) throws RouteIdNotExistsException;
}
