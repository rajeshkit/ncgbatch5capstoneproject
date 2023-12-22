package com.rajaparvathi.schedule.service;

import com.rajaparvathi.schedule.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;
import org.springframework.web.client.RestTemplate;
@Service
public class ScheduleService implements ScheduleServiceInterface{




        @Autowired
        private RestTemplate restTemplate;

        public  <Train,Route> Schedule createSchedule(Schedule newScheduleRequest) {
            Train train = fetchTrainByTrainId(newScheduleRequest.getTrainId());
            Route route = fetchRouteByRouteId(newScheduleRequest.getRouteId());

            if (train != null && route != null) {
                Schedule schedule = new Schedule();
                schedule.setDepartureDateTime(newScheduleRequest.getDepartureDateTime());
                schedule.setArrivalDateTime(newScheduleRequest.getArrivalDateTime());
                schedule.setTrain(train);
                schedule.setRoute(route);

                // Save the schedule to your database or perform necessary actions

                return schedule;
            } else {
                // Handle case when train or route is not found
                return null;
            }
        }
        public Schedule createSchedule(Schedule scheduleRequest) {
            return scheduleRequest;
        }

        private <Train> Train fetchTrainByTrainId(String trainId) {

            ResponseEntity responseEntity = restTemplate.getForEntity("http://train-microservice/trains/" + trainId, Train.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return (Train) responseEntity.getBody();
            }
            return null;
        }

        private <Route> Route fetchRouteByRouteId(String routeId) {
            ResponseEntity responseEntity = restTemplate.getForEntity("http://route-microservice/routes/" + routeId, Route.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return (Route) responseEntity.getBody();
            }
            return null;
        }
    }
}
