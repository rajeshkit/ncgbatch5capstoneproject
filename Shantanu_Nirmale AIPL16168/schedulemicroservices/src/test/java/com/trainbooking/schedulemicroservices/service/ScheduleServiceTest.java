package com.trainbooking.schedulemicroservices.service;

import com.trainbooking.schedulemicroservices.exception.ScheduleIdNotExistException;
import com.trainbooking.schedulemicroservices.model.Route;
import com.trainbooking.schedulemicroservices.model.Schedule;
import com.trainbooking.schedulemicroservices.model.ScheduleRequest;
import com.trainbooking.schedulemicroservices.model.Train;
import com.trainbooking.schedulemicroservices.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    ScheduleServiceImpl scheduleServiceImpl;


    @Test
    void getAllScheduleDetails() {
        ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                .scheduleId(999).trainNumber(171717).arrivalDateTime("2023-12-20T18:20:59.027+00:00")
                .departureDateTime("2023-12-20T18:30:59.027+00:00").routeId(111).build();

        ScheduleRequest scheduleRequest2 = ScheduleRequest.builder()
                .scheduleId(888).trainNumber(191919).arrivalDateTime("2023-12-21T18:20:59.027+00:00")
                .departureDateTime("2023-12-21T18:30:59.027+00:00").routeId(222).build();

        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(scheduleRequest1,scheduleRequest2));
        assertEquals(2,scheduleServiceImpl.getAllScheduleDetails().size());
    }

    @Test
    void getScheduleByScheduleId() throws ScheduleIdNotExistException {
        ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                .scheduleId(999).trainNumber(171717).arrivalDateTime("2023-12-20T18:20:59.027+00:00")
                .departureDateTime("2023-12-20T18:30:59.027+00:00").routeId(111).build();

        Mockito.when(scheduleRepository.findById(999)).thenReturn(Optional.of(scheduleRequest1));
        assertEquals(123,scheduleServiceImpl.getScheduleByScheduleId(999).getScheduleId());
    }

    @Test
    void getScheduleByScheduleIdWithException() {
        Mockito.when(scheduleRepository.findById(999)).thenReturn(Optional.empty());
        assertThrows(ScheduleIdNotExistException.class, ()-> scheduleServiceImpl.getScheduleByScheduleId(999));
    }

}