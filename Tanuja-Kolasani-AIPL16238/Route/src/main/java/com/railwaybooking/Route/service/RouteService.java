package com.railwaybooking.Route.service;

import com.railwaybooking.Route.exception.RouteIdNotFoundException;
import com.railwaybooking.Route.model.RouteInfo;

import java.util.List;

public interface RouteService {
    RouteInfo addRoute(RouteInfo routeInfo);

    List<RouteInfo> getAllRoutes();

    RouteInfo getRouteById(Long routeId) throws RouteIdNotFoundException;

    RouteInfo updateRouteInfo(RouteInfo routeInfo) throws RouteIdNotFoundException;

    String deleteRouteById(Long routeId) throws RouteIdNotFoundException;
}
