package com.booking_details.schedule.service;


import com.booking_details.route.exception.RouteIdNotFoundException;
import com.booking_details.route.model.RouteModel;
import com.booking_details.schedule.exception.ScheduleNotFoundException;
import com.booking_details.schedule.model.ScheduleModel;
import com.booking_details.schedule.model.ScheduleRequest;
import com.booking_details.schedule.repository.ScheduleRepository;
import com.booking_details.train.exception.TrainIdNotFoundException;
import com.booking_details.train.model.TrainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger LOGGER = Logger.getLogger("Schedule");
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ScheduleModel addScheduleDetails(ScheduleRequest scheduleRequest) throws TrainIdNotFoundException, RouteIdNotFoundException {
        LOGGER.log(Level.INFO, "Adding all schedule details....");
        long trainNumber = scheduleRequest.getTrainNumber();
        long routeId = scheduleRequest.getRouteId();
        ScheduleModel schedule = new ScheduleModel();
        TrainModel trainModel = restTemplate.getForObject("http://localhost:8081/train-microservice/train/" + trainNumber, TrainModel.class);
        RouteModel routeModel = restTemplate.getForObject("http://localhost:8082/route-microservice/route/" + routeId, RouteModel.class);
        if (trainModel != null) {
            if (routeModel != null) {
                schedule.setScheduleId(scheduleRequest.getScheduleId());
                schedule.setArrivalDateTime(scheduleRequest.getArrivalDateTime());
                schedule.setDepartureDateTime(scheduleRequest.getDepartureDateTime());
                schedule.setTrain(new TrainModel(trainModel.getTrainNumber(),trainModel.getTrainName(),trainModel.getTotalKms(),trainModel.getAcCoaches(),trainModel.getAcCoachTotalSeats(),trainModel.getSleeperCoaches(),trainModel.getSleeperCoachTotalSeats(),trainModel.getGeneralCoaches(),trainModel.getGeneralCoachesTotalSeats()));
                schedule.setRoute(new RouteModel(routeModel.getRouteId(),routeModel.getSource(),routeModel.getDestination(),routeModel.getTotalKms()));
                scheduleRepository.save(schedule);
            } else {
                throw new RouteIdNotFoundException();
            }
        } else {
            throw new TrainIdNotFoundException();
        }
        return schedule;
    }

    @Override
    public List<ScheduleModel> getScheduleDetails() {
        LOGGER.log(Level.INFO, "Fetching schedule details....");
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleModel getScheduleDetailsById(long scheduleId) throws ScheduleNotFoundException {
        LOGGER.log(Level.INFO, "Fetching schedule details by scheduleId....");
        Optional<ScheduleModel> schedule = scheduleRepository.findById(scheduleId);
        if (schedule.isEmpty()) {
            throw new ScheduleNotFoundException();
        }
        return schedule.get();
    }

    @Override
    public ScheduleModel updateScheduleDetails(ScheduleRequest scheduleRequest) throws ScheduleNotFoundException, TrainIdNotFoundException, RouteIdNotFoundException {
        LOGGER.log(Level.INFO, "Updating all Schedule details....");
        if (getScheduleDetailsById(scheduleRequest.getScheduleId()) != null) {
            return addScheduleDetails(scheduleRequest);
        }
        throw new ScheduleNotFoundException();
    }

    @Override
    public String deleteScheduleDetails(long scheduleId) throws ScheduleNotFoundException {

        ScheduleModel schedule = getScheduleDetailsById(scheduleId);
        if (schedule != null) {
            LOGGER.log(Level.INFO, "Deleting  all Schedule details....");
            scheduleRepository.deleteById(scheduleId);
            return "Schedule details deleted succesfully...";
        }
        throw new ScheduleNotFoundException();
    }
    @Override
    public ScheduleModel getScheduleDetailsByTrainId(Long trainNumber) {
        LOGGER.log(Level.INFO, "Fetching all Schedule details by trainId....");
        return scheduleRepository.findByTrain_TrainNumber(trainNumber);
    }
}



