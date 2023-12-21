package com.schedulemicroservice.service;

import com.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.schedulemicroservice.model.ScheduleRequest;
import com.schedulemicroservice.repository.ScheduleRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleRequestServiceImplTest {
        @Mock
        ScheduleRequestRepository scheduleRequestRepository;
        @InjectMocks
        ScheduleRequestServiceImpl scheduleRequestService;
    @Test
    void getAllScheduleRequest() {

        ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                .scheduleId(1)
                .trainId(12991)
                .arrivalDateTime("12-12-2023 10:10:00")
                .departureDateTime("12-12-2023 10:12:00")
                .routeId(1).build();

        ScheduleRequest scheduleRequest2 = ScheduleRequest.builder()
                .scheduleId(2)
                .trainId(12992)
                .arrivalDateTime("12-12-2023 10:10:00")
                .departureDateTime("12-12-2023 10:12:00")
                .routeId(123).build();
        Mockito.when(scheduleRequestRepository.findAll()).thenReturn(Arrays.asList(scheduleRequest1,scheduleRequest2));
            assertEquals(2,scheduleRequestService.getAllScheduleRequest().size());
    }


    @Test
    void getScheduleRequestById() throws ScheduleIdNotExistException {
        ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                .scheduleId(10)
                .trainId(12991)
                .arrivalDateTime("12-12-2023 10:10:00")
                .departureDateTime("12-12-2023 10:12:00")
                .routeId(1).build();
            Mockito.when(scheduleRequestRepository.findById(10)).thenReturn(Optional.of(scheduleRequest1));
            assertEquals(10,scheduleRequestService.getScheduleRequestById(10).getScheduleId());
        }
    @Test
    void getScheduleByScheduleIdWithException() {
        Mockito.when(scheduleRequestRepository.findById(11)).thenReturn(Optional.empty());
        assertThrows(ScheduleIdNotExistException.class, ()-> scheduleRequestService.getScheduleRequestById(11));
    }


    }
