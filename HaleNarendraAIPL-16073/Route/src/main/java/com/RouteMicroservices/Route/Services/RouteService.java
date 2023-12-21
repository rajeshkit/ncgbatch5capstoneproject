package com.RouteMicroservices.Route.Services;

import com.RouteMicroservices.Route.Exception.RouteIdNotFoundException;
import com.RouteMicroservices.Route.model.RouteResources;

import java.util.List;

public interface RouteService {

    RouteResources addRouteResources(RouteResources routeResources);

    List<RouteResources> getAllRouteDetail();

    RouteResources getRouteById(Long routeId) throws RouteIdNotFoundException;

    RouteResources updateRouteDetail(RouteResources routeResources) throws RouteIdNotFoundException;

    String deleteRouteById(Long routeId) throws RouteIdNotFoundException;
}
