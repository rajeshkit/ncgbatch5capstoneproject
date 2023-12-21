package com.altimetrik.schedulemicroservice.service;


import com.altimetrik.schedulemicroservice.exceptions.RouteNotExistsException;
import com.altimetrik.schedulemicroservice.exceptions.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Service
public class ScheduleServiceImplementation implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImplementation.class);

    private final RestTemplate restTemplate;
//    private final WebClient.Builder webClientBuilder;

//    @Autowired
//    public ScheduleServiceImplementation(WebClient.Builder webClientBuilder, ScheduleRepository scheduleRepository) {
//        this.webClientBuilder = webClientBuilder;
//        this.scheduleRepository =
//        scheduleRepository;
//    }
@Autowired
public ScheduleServiceImplementation(RestTemplate restTemplate, ScheduleRepository scheduleRepository){
    this.scheduleRepository=scheduleRepository;
    this.restTemplate = restTemplate;

}

    @Override
    public List<Schedule> getAllSchedules() {
        logger.info("Fetching all schedules");
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(String scheduleId) {
        logger.info("Fetching schedule by ID: {}", scheduleId);
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleIdNotExistsException("Schedule with ID " + scheduleId + " does not exist in the database."));
    }

    @Override
    public String deleteScheduleById(String scheduleId) {
        logger.info("Deleting schedule by ID: {}", scheduleId);
        String message = "Schedule does not exist to delete";
        Schedule schedule = getScheduleById(scheduleId);
        if (schedule != null) {
            scheduleRepository.deleteById(scheduleId);
            message = "Schedule deleted successfully";
        }
        return message;
    }

    @Override
    public Schedule addSchedule(Schedule schedule) throws RouteNotExistsException, TrainIdNotExistsException {
        logger.info("Adding schedule: {}", schedule);
        validateRouteAndTrain(schedule.getRouteId(), schedule.getTrainNumber());
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws RouteNotExistsException, TrainIdNotExistsException {
        logger.info("Updating schedule: {}", schedule);
        getScheduleById(schedule.getScheduleId());
        validateRouteAndTrain(schedule.getRouteId(), schedule.getTrainNumber());
        return scheduleRepository.save(schedule);
    }
//--------------------------USING WEBCLIENT---------------------------------------
//    void validateRouteAndTrain(String routeId, String trainNumber) throws RouteNotExistsException, TrainIdNotExistsException {
//        try {
//            if (!checkEntityExistence("http://localhost:8086/routes/{routeId}", routeId)) {
//                throw new RouteNotExistsException("No Route Exists with that ID");
//            }
//        } catch (Exception e) {
//            throw new RouteNotExistsException("Error checking route existence");
//        }
//
//        try {
//            if (!checkEntityExistence("http://localhost:8085/trains/{trainNumber}", trainNumber)) {
//                throw new TrainIdNotExistsException("No Train Exists with that ID");
//            }
//        } catch (Exception e) {
//            throw new TrainIdNotExistsException("Error checking train existence");
//        }
//    }

//    boolean checkEntityExistence(String url, String entityId) throws Exception {
//        try {
//            return Boolean.TRUE.equals(webClientBuilder.build()
//                    .get()
//                    .uri(url, entityId)
//                    .retrieve()
//                    .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new WebClientResponseException(
//                            response.rawStatusCode(),
//                            response.statusCode().getReasonPhrase(),
//                            response.headers().asHttpHeaders(),
//                            response.bodyToMono(String.class).map(String::getBytes).block(),
//                            response.headers().contentType().orElse(null).getCharset()
//                    )))
//                    .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new WebClientResponseException(
//                            response.rawStatusCode(),
//                            response.statusCode().getReasonPhrase(),
//                            response.headers().asHttpHeaders(),
//                            response.bodyToMono(String.class).map(String::getBytes).block(),
//                            response.headers().contentType().orElse(null).getCharset()
//                    )))
//                    .bodyToMono(Void.class)
//                    .thenReturn(true)
//                    .block());
//        } catch (Exception e) {
//            // Handle other exceptions, e.g., log or rethrow
//            throw new RuntimeException("Error checking entity existence", e);
//        }
//
//--------------------------USING WEBCLIENT---------------------------------------


//--------------------------USING REST TEMPLATE---------------------------------------
    public void validateRouteAndTrain(String routeId, String trainNumber) throws RouteNotExistsException, TrainIdNotExistsException {
        try {
            ResponseEntity<String> routeResponse = restTemplate.getForEntity("http://localhost:8086/routes/{routeId}", String.class, routeId);

            if (routeResponse.getStatusCode().is4xxClientError()) {
                throw new RouteNotExistsException("No Route Exists with that ID");
            } else {
                System.out.println("Route Data: " + routeResponse.getBody());
/*  Note: Can Map here to fetch Route Details here also, but that's unnecessary as I already have fetchRouteDetails with RouteId  */
            }
        } catch (Exception e) {
            throw new RouteNotExistsException("Error checking route existence");
        }

        try {
            ResponseEntity<String> trainResponse = restTemplate.getForEntity("http://localhost:8085/trains/{trainNumber}", String.class, trainNumber);

            if (trainResponse.getStatusCode().is4xxClientError()) {
                throw new TrainIdNotExistsException("No Train Exists with that ID");
            } else {
                System.out.println("Train Data: " + trainResponse.getBody());
/*     Note: Can Map to fetch Train Details here also, but that's unnecessary as I already have fetchTrainDetails with TrainNumber  */
            }
        } catch (Exception e) {
            throw new TrainIdNotExistsException("Error checking train existence");
        }
    }
//--------------------------USING REST TEMPLATE---------------------------------------

}
