package com.booking_details.route.service;

import com.booking_details.route.exception.RouteIdNotFoundException;
import com.booking_details.route.model.RouteModel;

import java.util.List;

public interface RouteService {
    RouteModel addRouteDetails(RouteModel routeModel);

    List<RouteModel> getAllRouteDetails();

    RouteModel getAllRouteDetailsById(Long routeId) throws RouteIdNotFoundException;

    RouteModel updateRouteDetails(RouteModel routeModel) throws RouteIdNotFoundException;

    String deleteRouteDetailsById(Long trainNumber) throws RouteIdNotFoundException;
}
