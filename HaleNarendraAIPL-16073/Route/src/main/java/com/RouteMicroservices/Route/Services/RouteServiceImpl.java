package com.RouteMicroservices.Route.Services;

import com.RouteMicroservices.Route.Exception.RouteIdNotFoundException;
import com.RouteMicroservices.Route.Repository.RouteResourcesRepository;
import com.RouteMicroservices.Route.model.RouteResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteResourcesRepository routeResourcesRepository;

    @Override
    public RouteResources addRouteResources(RouteResources routeResources) {
        return routeResourcesRepository.save(routeResources);
    }

    @Override
    public List<RouteResources> getAllRouteDetail() {

        return routeResourcesRepository.findAll();
    }

    @Override
    public RouteResources getRouteById(Long routeId) throws RouteIdNotFoundException {
        Optional<RouteResources> pro = routeResourcesRepository.findById(routeId);
        if (pro.isEmpty()) {
            throw new RouteIdNotFoundException("Route Id not found Exception!!!");
        }
        return pro.get();

    }

    @Override
    public RouteResources updateRouteDetail(RouteResources routeResources) throws RouteIdNotFoundException {
        if (getRouteById(routeResources.getRouteId()) != null) {
            return routeResourcesRepository.save(routeResources);
        }
        return null;
    }

    @Override
    public String deleteRouteById(Long routeId) throws RouteIdNotFoundException {
        String message = "Route detail Does not exists to delete";
        RouteResources p = getRouteById(routeId);
        if (p != null) {
            routeResourcesRepository.deleteById(routeId);
            message = "Route detail deleted successfully";
            return message;
        }
        return message;
    }
}


