package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    public Route addRoute(Route route);
    public List<Route> getAllRoute();
    public Route getRouteById(int routeId) throws RouteIdNotExistsException;
    public Route updateRoute(Route route) throws RouteIdNotExistsException;
    public String deleteRouteById(int routeId) throws RouteIdNotExistsException;
}
