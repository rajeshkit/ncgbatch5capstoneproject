package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdAlreadyExistsException;
import com.altimetrik.routemicroservice.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route) throws RouteIdNotExistsException, RouteIdAlreadyExistsException;
    List<Route> getAllRoute();
    Route getRouteById(int routeId) throws RouteIdNotExistsException;
    Route updateRoute(Route route) throws RouteIdNotExistsException;
    String deleteRouteById(int routeId) throws RouteIdNotExistsException;
}
