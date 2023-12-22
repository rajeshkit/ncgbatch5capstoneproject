package com.altimetrik.route.service;

import com.altimetrik.route.entity.Route;
import com.altimetrik.route.exception.RouteNotExistsException;
import com.altimetrik.route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService{
    private RouteRepository routeRepository;
    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route addRoute(Route route) {

        return routeRepository.save(route);
    }
    public Route getRouteById(int routeId) {
      return routeRepository.findById(routeId).get();

    }

    @Override
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }

    @Override
    public Route updateRoute(Route route) throws RouteNotExistsException {

        if (routeRepository.findById(route.getRouteId())!=null) {
            return routeRepository.save(route);
        }
        throw new RouteNotExistsException("Route Number is not Exit!!! Please enter valid Train Number");
    }

    @Override
    public String deleteRoute(int routeId) throws RouteNotExistsException {
        if (routeRepository.findById(routeId)!=null){
            routeRepository.deleteById(routeId);
            return "Deleted successfully";
        }else{
            throw new RouteNotExistsException("Train Number is not Exit!!! please enter valid number to delete");
        }
    }
}
