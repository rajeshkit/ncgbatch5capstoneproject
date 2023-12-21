package com.routemicroservice.service;

import com.routemicroservice.exception.RouteIdDoesNotExistException;
import com.routemicroservice.model.Route;

import java.util.List;

public interface RouteService {
    Route saveRouteDetails(Route route);
    List<Route> getAllRoutesDetail();
    Route getRouteDetailsById(int routeId) throws RouteIdDoesNotExistException;
    Route updateRouteDetails(Route route) throws RouteIdDoesNotExistException;
    String deleteRouteDetails(int routeId) throws RouteIdDoesNotExistException;
}