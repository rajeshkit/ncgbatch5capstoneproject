package com.altimetrik.trainschedule.service;

import com.altimetrik.trainschedule.exception.RouteIdNotFoundException;
import com.altimetrik.trainschedule.exception.ScheduleIdNotFoundException;
import com.altimetrik.trainschedule.exception.TrainNumberNotFoundException;
import com.altimetrik.trainschedule.modle.NewSchedule;
import com.altimetrik.trainschedule.modle.Route;
import com.altimetrik.trainschedule.modle.Schedule;
import com.altimetrik.trainschedule.modle.Train;
import com.altimetrik.trainschedule.repository.RouteRepository;
import com.altimetrik.trainschedule.repository.ScheduleRepository;
import com.altimetrik.trainschedule.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceImpTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TrainRepository trainRepository;

    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private ScheduleServiceImp scheduleServiceImp;

    @Test
    void addSchedule() throws Exception{
        when(restTemplate.getForObject(anyString(), eq(Train.class))).thenReturn(new Train());
        when(restTemplate.getForObject(anyString(), eq(Route.class))).thenReturn(new Route());

        when(scheduleRepository.save(any())).thenReturn(new Schedule());

        NewSchedule newSchedule = new NewSchedule();
        newSchedule.setTrainNumber(16591);
        newSchedule.setRouteId(4620);

        assertNotNull(null);
        verify(scheduleRepository, times(1)).save(any());
    }

    @Test
    void getAllSchedule() {
        Schedule s1 = Schedule.builder().scheduleId(1).build();
        Schedule s2 = Schedule.builder().scheduleId(2).build();
        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(s1,s2));
        List<Schedule> result = scheduleServiceImp.getAllSchedule();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getScheduleId());
    }

    @Test
    void getScheduleById() throws ScheduleIdNotFoundException {
        int scheduleId = 1;
        Schedule mockSchedule = Schedule.builder().scheduleId(scheduleId).build();
        Optional<Schedule> optionalSchedule = Optional.of(mockSchedule);
        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(optionalSchedule);
        Schedule result = scheduleServiceImp.getScheduleById(scheduleId);
        assertNotNull(result);
        assertEquals(scheduleId, result.getScheduleId());
        verify(scheduleRepository, times(1)).findById(scheduleId);
    }

    @Test
    void updateSchedule() throws ScheduleIdNotFoundException {
        int scheduleId = 1;
        Schedule existingSchedule = Schedule.builder().scheduleId(scheduleId).build();
        Schedule updatedSchedule = Schedule.builder().scheduleId(scheduleId).build();
        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(existingSchedule));
        Mockito.when(scheduleRepository.save(updatedSchedule)).thenReturn(updatedSchedule);
        Schedule result = scheduleServiceImp.updateSchedule(updatedSchedule);
        assertNotNull(result);
        assertEquals(scheduleId, result.getScheduleId());
        verify(scheduleRepository, times(1)).findById(scheduleId);
        verify(scheduleRepository, times(1)).save(updatedSchedule);
    }

    @Test
    void deleteSchedule() throws ScheduleIdNotFoundException {
        int scheduleId = 1;
        Schedule existingSchedule = Schedule.builder().scheduleId(scheduleId).build();
        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(existingSchedule));
        String result = scheduleServiceImp.deleteScheduleById(scheduleId);
        assertEquals("Schedule successfully deleted", result);
        verify(scheduleRepository, times(1)).findById(scheduleId);
        verify(scheduleRepository, times(1)).deleteById(scheduleId);
    }
}
