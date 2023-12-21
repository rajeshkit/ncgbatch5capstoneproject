package com.railways.route.services;

import com.railways.route.exceptions.RouteIdNotFound;
import com.railways.route.model.Route;

import java.util.List;

public interface RouteServices {
    Route addRouteDeatils(Route route);

    Route getRouteById(long routeIdToSearch) throws RouteIdNotFound;

    List<Route> getAllRoutes();

    Route updateByRouteId(Route route)throws RouteIdNotFound;

    String deleteByRouteId(long routeIdToDelete)throws RouteIdNotFound;
}
