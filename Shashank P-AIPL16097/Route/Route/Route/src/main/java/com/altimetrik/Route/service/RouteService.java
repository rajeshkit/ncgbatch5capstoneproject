package com.altimetrik.Route.service;

import com.altimetrik.Route.exception.RouteIdNotExistsException;
import com.altimetrik.Route.model.Route;

import java.util.List;

public interface RouteService {

    Route addRoute(Route route);
    List<Route> getAllRoutes();
    Route getRouteById(int routeId) throws RouteIdNotExistsException;
    Route updateRoute(Route route) throws RouteIdNotExistsException;
    String deleteRouteById(int routeId) throws RouteIdNotExistsException;
}
