package com.altimetrik.trainschedule.service;

import com.altimetrik.trainschedule.exception.RouteIdNotExistsException;
import com.altimetrik.trainschedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.trainschedule.exception.TrainNoNotExistsException;
import com.altimetrik.trainschedule.model.Schedule;
import com.altimetrik.trainschedule.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)

public class ScheduleServiceImplTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleServiceImpl scheduleServiceImpl;

    @Test
    void getAllSchedule(){
        Schedule s1 = Schedule.builder()
                .scheduleId("100")
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now().plusHours(3)).trainId("f2").
                routeId("r3").build();
        Schedule s2 = Schedule.builder()
                .scheduleId("101")
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now().plusHours(3)).trainId("m2").
                routeId("g2").build();
        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(s1,s2));
        assertEquals(2,scheduleServiceImpl.getAllSchedule().size());
    }

    @Test
    void SchedulegetById() throws ScheduleIdNotExistsException {
        Schedule s1 = Schedule.builder()
                .scheduleId("100")
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now().plusHours(3)).trainId("f2").
                routeId("r3").build();
        Mockito.when(scheduleRepository.findById("100")).thenReturn(Optional.of(s1));
        assertEquals("100",scheduleServiceImpl.getScheduleById("100").getScheduleId());
    }

    @Test
    void getScheduleByIdWithException()  {
        Schedule s1 = Schedule.builder()
                .scheduleId("100")
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now().plusHours(3)).trainId("f2").
                routeId("r3").build();
        Mockito.when(scheduleRepository.findById("400")).thenReturn(Optional.empty());
        assertThrows(ScheduleIdNotExistsException.class,
                ()->{scheduleServiceImpl.getScheduleById("400");});
    }
}
