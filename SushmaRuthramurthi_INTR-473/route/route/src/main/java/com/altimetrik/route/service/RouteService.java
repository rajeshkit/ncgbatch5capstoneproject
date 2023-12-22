package com.altimetrik.route.service;

import com.altimetrik.route.entity.Route;
import com.altimetrik.route.exception.RouteNotExistsException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RouteService {
    public Route addRoute(Route route);
    public Route getRouteById(int routeId);
    public List<Route> getAllRoute();
    public Route updateRoute(Route route) throws RouteNotExistsException;
    public String deleteRoute(int routeId) throws RouteNotExistsException;
}
