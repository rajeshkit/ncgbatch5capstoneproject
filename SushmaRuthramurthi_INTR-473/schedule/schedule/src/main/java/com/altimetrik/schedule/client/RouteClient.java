package com.altimetrik.schedule.client;


import com.altimetrik.schedule.Dto.Route;
import com.altimetrik.schedule.Dto.Train;
import com.altimetrik.schedule.exception.InvalidRouteException;

import java.util.List;

public interface RouteClient {
    public List<Route> getAllRoutes() throws InvalidRouteException;
    public Route getRouteById(int routeId) throws InvalidRouteException;
}
