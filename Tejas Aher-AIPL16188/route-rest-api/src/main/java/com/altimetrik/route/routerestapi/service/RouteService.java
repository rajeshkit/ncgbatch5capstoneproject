package com.altimetrik.route.routerestapi.service;

import com.altimetrik.route.routerestapi.exception.RouteNumberNotFoundException;
import com.altimetrik.route.routerestapi.model.Route;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RouteService {

    public Route addRoute(@RequestBody Route route);

    public List<Route> getAllRoute();

    public Route getRouteById(String routeId) throws RouteNumberNotFoundException;

    public Route updateRoute(Route route, String routeId) throws RouteNumberNotFoundException;

    public String deleteRouteByID(String routeId) throws RouteNumberNotFoundException;
}
