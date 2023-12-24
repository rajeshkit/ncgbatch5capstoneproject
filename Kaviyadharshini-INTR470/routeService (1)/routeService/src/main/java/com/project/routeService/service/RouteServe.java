package com.project.routeService.service;

import com.project.routeService.exception.RouteNotFoundException;
import com.project.routeService.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteServe {

    public Route addRoute(Route route);

    public List<Route> getAllRoutes();

    public Optional<Route> getRouteById(int routeId) throws RouteNotFoundException;

    public String deleteRoute(int routeId) throws RouteNotFoundException;

    public Route updateRoute(Route route ) throws RouteNotFoundException;


}
