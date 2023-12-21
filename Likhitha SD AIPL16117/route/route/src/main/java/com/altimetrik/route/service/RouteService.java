package com.altimetrik.route.service;

import java.util.List;
import com.altimetrik.route.exception.RouteNotExistsException;
import com.altimetrik.route.model.Route;


public interface RouteService {
    Route addRoute(Route route);

    List<Route> getAllroute();

    Route getRouteById(int routeId) throws RouteNotExistsException;

    Route updateRoute(Route route) throws RouteNotExistsException;

    String deleteRouteById(int routeId) throws RouteNotExistsException;


}
