package com.altimetrik.schedulemicroservice.ServiceTest;

import com.altimetrik.schedulemicroservice.exception.ScheduleNotFoundException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import com.altimetrik.schedulemicroservice.service.ScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Test
    void createSchedule() {
        NewScheduleRequest newScheduleRequest = new NewScheduleRequest();
        newScheduleRequest.setDepartureDateTime(LocalDateTime.now().toString());
        newScheduleRequest.setArrivalDateTime(LocalDateTime.now().plusHours(2).toString());
        newScheduleRequest.setTrainId(1);
        newScheduleRequest.setRouteId(1);

        Train train = new Train();
        train.setTrainNumber(1);

        Route route = new Route();
        route.setRouteId(1);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Train.class), Mockito.anyInt())).thenReturn(train);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Route.class), Mockito.anyInt())).thenReturn(route);
        Mockito.when(scheduleRepository.save(any(Schedule.class))).thenReturn(new Schedule());

        Schedule createdSchedule = scheduleService.createSchedule(newScheduleRequest);
        assertNotNull(createdSchedule);
    }

    @Test
    void getAllSchedules() {
        Schedule schedule = new Schedule();
        Mockito.when(scheduleRepository.findAll()).thenReturn(Collections.singletonList(schedule));

        List<Schedule> schedules = scheduleService.getAllSchedules();
        assertFalse(schedules.isEmpty());
        assertEquals(1, schedules.size());
    }

    @Test
    void getScheduleById() throws ScheduleNotFoundException {
        int scheduleId = 1;
        Schedule schedule = new Schedule();
        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(schedule));

        Schedule retrievedSchedule = scheduleService.getScheduleById(scheduleId);
        assertNotNull(retrievedSchedule);
    }

    @Test
    void updateSchedule() throws ScheduleNotFoundException {
        Schedule updatedSchedule = new Schedule();
        updatedSchedule.setScheduleId(1);

        Mockito.when(scheduleRepository.findById(updatedSchedule.getScheduleId())).thenReturn(Optional.of(updatedSchedule));
        Mockito.when(scheduleRepository.save(updatedSchedule)).thenReturn(updatedSchedule);

        Schedule result = scheduleService.updateSchedule(updatedSchedule);
        assertNotNull(result);
        assertEquals(updatedSchedule.getScheduleId(), result.getScheduleId());
    }

    @Test
    void deleteScheduleById() throws ScheduleNotFoundException {
        int scheduleId = 1;

        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(new Schedule()));

        String result = scheduleService.deleteScheduleById(scheduleId);
        assertEquals("Schedule deleted successfully", result);
    }
}
