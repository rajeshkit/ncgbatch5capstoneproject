package com.altimetrik.trainroute.service;

import com.altimetrik.trainroute.exception.RouteIdNotExistsException;
import com.altimetrik.trainroute.model.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route);

    List<Route> getAllRoute();

    Route getRouteById(int routeId) throws RouteIdNotExistsException;

    Route updateRoute(Route route) throws RouteIdNotExistsException;

    String deleteRouteById(int routeId) throws RouteIdNotExistsException;
}
