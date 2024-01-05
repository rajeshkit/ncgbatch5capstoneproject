package com.trainbooking.routemicroservices.service;

import com.trainbooking.routemicroservices.exception.RouteIdNotExistException;
import com.trainbooking.routemicroservices.model.Route;

import java.util.List;

public interface RouteService {
     Route addRoute(Route route);

     List<Route> getAllRoutes();

     Route getRouteByRouteId(int routeId) throws RouteIdNotExistException;

     Route updateRouteDetails(Route route) throws RouteIdNotExistException;

     String deleteRouteByRouteId(int routeId)throws RouteIdNotExistException;
}
