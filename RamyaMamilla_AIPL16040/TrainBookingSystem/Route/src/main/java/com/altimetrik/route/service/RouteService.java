package com.altimetrik.route.service;

import com.altimetrik.route.exception.RouteIDNotFoundException;
import com.altimetrik.route.model.Route;

import java.util.List;

public interface RouteService {
    public Route addRoute(Route route);
    public List<Route> viewAllRoutes();
    public Route getRouteById(int routeId) throws RouteIDNotFoundException;
    public Route updateRoute(Route route) throws RouteIDNotFoundException;
    public String deleteRouteById(int routeId) throws RouteIDNotFoundException;
}
