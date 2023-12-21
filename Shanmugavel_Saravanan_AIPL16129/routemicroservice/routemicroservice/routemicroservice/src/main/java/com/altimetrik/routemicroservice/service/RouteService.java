package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdAlreadyExists;
import com.altimetrik.routemicroservice.exception.RouteNotFoundException;
import com.altimetrik.routemicroservice.model.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route) throws RouteIdAlreadyExists;
    List<Route> getAllRoutes();
    Route getRouteById(int routeId) throws RouteNotFoundException;
    Route updateRoute(Route route) throws RouteNotFoundException;
    String deleteRouteById(int routeId) throws RouteNotFoundException;
}
