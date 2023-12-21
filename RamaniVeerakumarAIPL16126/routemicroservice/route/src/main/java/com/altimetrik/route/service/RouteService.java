package com.altimetrik.route.service;

import com.altimetrik.route.exception.RouteIdDoNotExitsException;
import com.altimetrik.route.model.Route;

import java.util.List;

public interface RouteService {
    public Route getRouteById(int routeId) throws RouteIdDoNotExitsException;
    public Route addRoute(Route route);
    List<Route> getAllRoutes();
    Route updateRoute(Route route) throws RouteIdDoNotExitsException;

    String deleteRouteById(int routeId) throws RouteIdDoNotExitsException;
}
