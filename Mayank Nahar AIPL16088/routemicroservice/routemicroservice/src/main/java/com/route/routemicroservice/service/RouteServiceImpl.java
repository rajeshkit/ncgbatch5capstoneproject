package com.route.routemicroservice.service;
import com.route.routemicroservice.repository.RouteRepository;
import com.route.routemicroservice.exception.RouteIdNotFoundException;
import com.route.routemicroservice.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class RouteServiceImpl implements RouteService{
    @Autowired
    private RouteRepository routeRepository;
    @Override
    public Route addRoute(Route route) {
        log.info("Route Added Successfully{}",route);
        return routeRepository.save(route);
    }
    @Override
    public List<Route> getAllRoute() {
        log.info("Route Details Fetch Successfully");
        return routeRepository.findAll();
    }
    @Override
    public Route getRouteById(int routeId) throws RouteIdNotFoundException {

        Optional<Route> routeOptional= routeRepository.findById(routeId);
        if(routeOptional.isEmpty()){
            log.info("Route By Id Not Fetch");
            throw new RouteIdNotFoundException("Route Id Not Found In Database Please Check Details");

        }
        log.info("Route By Id Successfully Fetch");
        return routeOptional.get();
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotFoundException {
        if(getRouteById(route.getRouteId())!=null){
            log.info("Route Update Successfully");
            return routeRepository.save(route);
        }
        return null;
    }

    @Override
    public String deleteRouteById(int routeId) throws RouteIdNotFoundException {
       String msg="Route Id Not Exist";
        Route r=getRouteById(routeId);
       if(r!=null){
           routeRepository.deleteById(routeId);
           msg="Route Deleted";
           log.info("Route Deleted Successfully");
           return msg;
       }
        return msg;
    }
}
