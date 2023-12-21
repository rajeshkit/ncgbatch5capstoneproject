package com.Booking.route.service;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.route.model.RouteResources;

import java.util.List;

public interface RouteService {
    RouteResources addRoute(RouteResources routeResources);

    List<RouteResources> getRoute();

    RouteResources getRouteById(Long routeId) throws RouteNotFindException;

    RouteResources updateRoute(RouteResources routeResources) throws RouteNotFindException;

    String deleteRoute(Long routeId) throws RouteNotFindException;
}
