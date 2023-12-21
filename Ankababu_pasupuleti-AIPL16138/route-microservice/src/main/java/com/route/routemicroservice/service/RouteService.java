package com.route.routemicroservice.service;

import com.route.routemicroservice.model.Route;

import java.util.List;

public interface RouteService {
    public Route addRoute(Route route);
    public List<Route> getAllRoutes();
    public Route getRouteByRouteId(int routeId);
    public Route updateRoute(Route route);
    public void deleteRouteByRouteId(int routeId);

}
