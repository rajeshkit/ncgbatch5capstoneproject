package com.altimetrikfinalproject.routemicroservice.service;

import com.altimetrikfinalproject.routemicroservice.exception.RouteDoesNotExistException;
import com.altimetrikfinalproject.routemicroservice.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    public Route addRoute(Route route);
    public List<Route> getAllRoute();
    public Optional<Route> getRouteById(int routeId) throws RouteDoesNotExistException;
    public Route updateRoute(Route route) throws RouteDoesNotExistException;
    public String deleteRoute(int routeId) throws RouteDoesNotExistException;
}
