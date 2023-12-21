package com.altimetrik.trainroute.service;

import com.altimetrik.trainroute.exception.RouteIdNotExistsException;
import com.altimetrik.trainroute.model.Route;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route);

    List<Route> getAllRoute();

    Route getRouteById(String routeId) throws RouteIdNotExistsException;

    Route updateRoute(Route route) throws RouteIdNotExistsException;

    String deleteRouteById(String routeId) throws RouteIdNotExistsException;
}
