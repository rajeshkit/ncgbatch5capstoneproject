package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdNotExistException;
import com.altimetrik.routemicroservice.model.Route;

import java.util.List;

public interface RouteService {
    public Route addRouteDetail(Route route);

    public Route getRouteById(int routeId) throws RouteIdNotExistException;

    public Route updateRouteById(Route route) throws RouteIdNotExistException;

   public String deleteRouteById(int routeId) throws RouteIdNotExistException;

    public List<Route> getAllRouteDetails();
}
