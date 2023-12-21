package com.booking_details.route.service;

import com.booking_details.route.exception.RouteIdNotFoundException;
import com.booking_details.route.model.RouteModel;
import com.booking_details.route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RouteServiceImpl implements RouteService{

    private static final Logger LOGGER = Logger.getLogger("Route");
    @Autowired
    private RouteRepository routeRepository;
    @Override
    public RouteModel addRouteDetails(RouteModel routeModel) {

        LOGGER.log(Level.INFO, "Adding the given route details....");
        return routeRepository.save(routeModel);
    }

    @Override
    public List<RouteModel> getAllRouteDetails() {

        LOGGER.log(Level.INFO, "Fetching all route details....");
        return routeRepository.findAll();
    }

    @Override
    public RouteModel getAllRouteDetailsById(Long routeId) throws RouteIdNotFoundException {
        LOGGER.log(Level.INFO, "Fetching all route details by routeId....");
        Optional<RouteModel> details= routeRepository.findById(routeId);
        if(details.isPresent()){
            return details.get();
        }

        throw new RouteIdNotFoundException();

    }

    @Override
    public RouteModel updateRouteDetails(RouteModel routeModel) throws RouteIdNotFoundException {
        LOGGER.log(Level.INFO, "Updating all route details....");
        if(getAllRouteDetailsById(routeModel.getRouteId())!=null)
        {
            return routeRepository.save(routeModel);
        }
        throw new RouteIdNotFoundException();
    }

    @Override
    public String deleteRouteDetailsById(Long routeId) throws RouteIdNotFoundException {
        RouteModel routeModel=getAllRouteDetailsById(routeId);
        if(routeModel!=null)
        {
            LOGGER.log(Level.INFO, "Deleting route details by routeId....");
            routeRepository.deleteById(routeId);
            return "Route details for this route id deleted successfully";
        }
        throw new RouteIdNotFoundException();
    }

}
