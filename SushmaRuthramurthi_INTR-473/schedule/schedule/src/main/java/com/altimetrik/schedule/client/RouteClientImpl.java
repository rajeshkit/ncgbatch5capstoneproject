package com.altimetrik.schedule.client;

import com.altimetrik.schedule.Dto.Route;
import com.altimetrik.schedule.Dto.Train;

import com.altimetrik.schedule.exception.InvalidRouteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouteClientImpl implements RouteClient{

    @Override
    public List<Route> getAllRoutes() throws InvalidRouteException {
        RestTemplate restTemplate = new RestTemplate();
        String routeResourceUrl = "http://localhost:8081/api/v1/route";
        ResponseEntity<Route[]> response = restTemplate.getForEntity(routeResourceUrl, Route[].class);
        if(response.getStatusCode() == HttpStatus.OK)
        {
           List<Route> route=Arrays.stream(response.getBody()).collect(Collectors.toList());
            return route;
        }
        else {
            throw new InvalidRouteException();
        }
    }

    @Override
    public Route getRouteById(int routeId) throws InvalidRouteException {
        RestTemplate restTemplate = new RestTemplate();
        String routeResourceUrl = "http://localhost:8081/api/v1/route/"+routeId;
        ResponseEntity<Route> response = restTemplate.getForEntity(routeResourceUrl, Route.class);
        if(response.getStatusCode() == HttpStatus.OK)
        {
            return response.getBody();
        }
        else
            throw new InvalidRouteException("Invalid Route Id:"+routeId);
    }

}

