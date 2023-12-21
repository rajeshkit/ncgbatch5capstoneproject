package com.booking.route.service;

import com.booking.route.exception.RouteIdNotExistsException;
import com.booking.route.model.RouteResources;

import java.util.List;

public interface RouteService {
    RouteResources addRouteResources(RouteResources routeResources);

    List<RouteResources> getAllRouteResources();

    RouteResources getAllRouteResourcesById(Long routeId) throws RouteIdNotExistsException;

    RouteResources updateRouteResource(RouteResources routeResources) throws RouteIdNotExistsException;

    String deleteRouteResourceById(Long trainNumber) throws RouteIdNotExistsException;
}
