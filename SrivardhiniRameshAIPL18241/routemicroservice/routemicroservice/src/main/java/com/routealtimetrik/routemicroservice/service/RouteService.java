package com.routealtimetrik.routemicroservice.service;

import com.routealtimetrik.routemicroservice.exception.RouteIdAlreadyExistException;
import com.routealtimetrik.routemicroservice.exception.RouteIdNotExistException;
import com.routealtimetrik.routemicroservice.model.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route) throws RouteIdAlreadyExistException;
    List<Route> getAllRoutes();
    Route getRouteById(int routeId) throws RouteIdNotExistException;
    Route updateRoute(Route route) throws RouteIdNotExistException;
    String deleteRouteById(int routeId) throws RouteIdNotExistException;

}
