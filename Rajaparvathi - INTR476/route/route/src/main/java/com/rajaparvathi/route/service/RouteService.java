package com.rajaparvathi.route.service;

import com.rajaparvathi.route.model.Route;

import java.util.List;

public interface RouteService {

    Route addRouteDetails(Route route);
    List<Route> getAllRoutes();
    Route searchRouteById(int routeId);
    Route updateDetails(Route route);
    String removeRouteById(int routeId);
}
