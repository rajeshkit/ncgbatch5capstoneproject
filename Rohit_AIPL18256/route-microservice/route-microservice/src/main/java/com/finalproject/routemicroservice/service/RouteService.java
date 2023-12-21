package com.finalproject.routemicroservice.service;

import com.finalproject.routemicroservice.exception.RouteIsNotExistException;
import com.finalproject.routemicroservice.model.Route;
import jakarta.servlet.http.PushBuilder;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    public Route addRoute(Route route);

    public List<Route> getAllRoutes();

    public Optional<Route> getRouteById(int routeId) throws RouteIsNotExistException;

    public String deleteRoute(int routeId) throws RouteIsNotExistException;

    public Route updateRoute(Route route ) throws RouteIsNotExistException;


}
