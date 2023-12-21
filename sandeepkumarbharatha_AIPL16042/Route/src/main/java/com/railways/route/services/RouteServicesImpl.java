package com.railways.route.services;

import com.railways.route.exceptions.RouteIdNotFound;
import com.railways.route.model.Route;
import com.railways.route.respository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServicesImpl implements RouteServices {
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRouteDeatils(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route getRouteById(long routeIdToSearch) throws RouteIdNotFound {
        Optional<Route> r = routeRepository.findById(routeIdToSearch);
        if (r.isEmpty()) {
                throw new RouteIdNotFound("Route  ID not found sandeep try other ID");
        }
        return r.get();
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route updateByRouteId(Route route) throws RouteIdNotFound {
        if (getRouteById(route.getRouteId()) == null) {
          throw new RouteIdNotFound("Route ID not found sandeep try other ID");
        }
        return routeRepository.save(route);
    }

    @Override
    public String deleteByRouteId(long routeIdToDelete) throws RouteIdNotFound {
        if (getRouteById(routeIdToDelete) != null) {
            routeRepository.deleteById(routeIdToDelete);
            return "details are sucessfuly deleted  " + routeIdToDelete;
        }
        throw new RouteIdNotFound("Route ID not found sandeep try other ID");
    }


}
