package com.altimetrik.route.sevice;

import com.altimetrik.route.exception.RouteIdNotExistException;
import com.altimetrik.route.model.Route;

import java.util.List;

public interface RouteService {

    public Route addRoute(Route route);

    public List<Route> getAllRoutes();

    public Route getRouteByRouteId(int routeId) throws RouteIdNotExistException;

    public Route updateRoute(Route route) throws RouteIdNotExistException;

    public String deleteRouteById(int routeId) throws RouteIdNotExistException;

}
