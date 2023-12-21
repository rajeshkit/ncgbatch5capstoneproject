package com.booking.route.service;

import com.booking.route.exception.RouteIdNotExistsException;
import com.booking.route.model.RouteResources;
import com.booking.route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{
    private RouteRepository routeRepository;
    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public RouteResources addRouteResources(RouteResources routeResources) {
        return routeRepository.save(routeResources);
    }

    @Override
    public List<RouteResources> getAllRouteResources() {
        return routeRepository.findAll();
    }

    @Override
    public RouteResources getAllRouteResourcesById(Long routeId) throws RouteIdNotExistsException {
        Optional<RouteResources> pro= routeRepository.findById(routeId);
        if(pro.isEmpty())
        {
            throw new RouteIdNotExistsException("Route Id is not exists in the db table!!! check the Route Id");
        }
        return pro.get();
    }

    @Override
    public RouteResources updateRouteResource(RouteResources routeResources) throws RouteIdNotExistsException {
        if(getAllRouteResourcesById(routeResources.getRouteId())!=null)
        {
            return routeRepository.save(routeResources);
        }
        return null;
    }

    @Override
    public String deleteRouteResourceById(Long routeId) throws RouteIdNotExistsException {
        String message="Route is Not Available by this Route Id";
        RouteResources routeResources=getAllRouteResourcesById(routeId);
        if(routeResources!=null)
        {
            routeRepository.deleteById(routeId);
            message="Route Resource deleted by this Route Id successfully";
            return message;
        }
        return  message;
    }


}
