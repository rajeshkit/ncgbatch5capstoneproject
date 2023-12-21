package Service;

import Model.Schedule;
import Repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    public void testGetScheduleDetailsById() {
        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDepartureDateTime(LocalDateTime.parse("Departure"));
        schedule.setArrivalDateTime(LocalDateTime.parse("Arrival"));

        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));

        Schedule fetchedSchedule = scheduleService.getScheduleDetailsById(1L);

        assertEquals(1L, fetchedSchedule.getScheduleId());
        assertEquals("Departure", fetchedSchedule.getDeparture());
        assertEquals("Arrival", fetchedSchedule.getArrival());
        assertEquals("Monday", fetchedSchedule.getDay());
    }
}
