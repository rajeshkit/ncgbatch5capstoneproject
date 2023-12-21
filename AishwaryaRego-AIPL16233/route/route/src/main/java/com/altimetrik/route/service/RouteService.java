package com.altimetrik.route.service;

import com.altimetrik.route.exception.RouteAlreadyExistsException;
import com.altimetrik.route.exception.RouteIdNotExistsException;
import com.altimetrik.route.model.Route;

import java.util.List;

public interface RouteService {
    List<Route> getAllRoutes();
    Route getRouteById(int routeId) throws RouteIdNotExistsException;
    Route addRoute(Route route)throws RouteAlreadyExistsException;
    Route updateRoute(Route route) throws RouteIdNotExistsException;
    String deleteRoute(int routeId) throws RouteIdNotExistsException;
}
