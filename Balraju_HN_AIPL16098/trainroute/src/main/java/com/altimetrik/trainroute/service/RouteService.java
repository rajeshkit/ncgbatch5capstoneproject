package com.altimetrik.trainroute.service;

import com.altimetrik.trainroute.exception.NoSuchElementException;
import com.altimetrik.trainroute.modle.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(Route route);

    List<Route> getAllRoute();

    Route getRouteById(int routeId) throws NoSuchElementException;

    Route updateRoute(Route route) throws NoSuchElementException;

    String deleteRouteById(int routeId) throws NoSuchElementException;
}
