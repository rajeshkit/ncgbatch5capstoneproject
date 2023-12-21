package com.railways.route.service;

import com.railways.route.exception.RouteIdExistsException;
import com.railways.route.exception.RouteNotFindException;
import com.railways.route.model.Route;

import java.util.List;

public interface RouteService {
    public Route addRoute(Route route) throws RouteIdExistsException;
    public List<Route> getRoute();

    public Route getRouteById(Long routeId) throws RouteNotFindException;

    public Route updateRoute(Route route) throws RouteNotFindException;

   public  String deleteRoute(Long routeId) throws RouteNotFindException;
}
