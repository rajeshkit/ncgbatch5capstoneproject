package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.respository.ScheduleRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplementationTest {

    @Mock
    ScheduleRepository scheduleRepository;
    @InjectMocks
    ScheduleServiceImplementation scheduleServiceImplementation;

    @Test
    void getAllScheduleDetails() {
            ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                    .scheduleId(1)
                    .trainId(19208)
                    .arrivalDateTime(new Date())
                    .departureDateTime(new Date())
                    .routeId(1).build();

            ScheduleRequest scheduleRequest2 = ScheduleRequest.builder()
                    .scheduleId(2)
                    .trainId(19208)
                    .arrivalDateTime(new Date())
                    .departureDateTime(new Date())
                    .routeId(2).build();

            Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(scheduleRequest1, scheduleRequest2));
            assertEquals(2,scheduleServiceImplementation.getAllScheduleDetails().size());
        }

    @Test
    void getScheduleById() throws ScheduleIdNotExistException {
        ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                .scheduleId(0)
                .trainId(19208)
                .arrivalDateTime(new Date())
                .departureDateTime(new Date())
                .routeId(1).build();

        Mockito.when(scheduleRepository.findById(0)).thenReturn(Optional.of(scheduleRequest1));
        assertEquals(0,scheduleServiceImplementation.getScheduleById(0).getScheduleId());
    }

    @Test
    void getScheduleByScheduleIdWithException() {
        Mockito.when(scheduleRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ScheduleIdNotExistException.class, () -> scheduleServiceImplementation.getScheduleById(1));

    }
}
