package com.route.routemicroservice.service;

import com.route.routemicroservice.exception.RouteIdNotFoundException;
import com.route.routemicroservice.model.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route);
    List<Route> getAllRoute();
    Route getRouteById(int routeId) throws RouteIdNotFoundException;
    Route updateRoute(Route route) throws RouteIdNotFoundException;
    String deleteRouteById(int routeId) throws RouteIdNotFoundException;

}
