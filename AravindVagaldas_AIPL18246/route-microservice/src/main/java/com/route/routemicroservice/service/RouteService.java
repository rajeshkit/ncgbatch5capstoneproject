package com.route.routemicroservice.service;

import com.route.routemicroservice.model.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route);
    List<Route> getAllRoutes();
    Route getRouteByRouteId(int routeId);
    Route updateRoute(Route route);
    String deleteRouteByRouteId(int routeId);

}
