package Controller;

import Config.ScheduleService;
import Model.Schedule;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ScheduleService schedulerService;

    @InjectMocks
    private ScheduleController scheduleController;

    @Test
    public void testScheduleTrainRoute() throws Exception {
        Schedule schedule = new Schedule();

        when(schedulerService.scheduleTrainRoute(1L, 1L)).thenReturn(schedule);

        mockMvc.perform(MockMvcRequestBuilders.get("/schedules/schedule/1/1"))
                .andExpect(status().isOk())
                // Add assertions for response body or other expectations
                .andExpect(jsonPath("$.train").exists())
                .andExpect(jsonPath("$.route").exists());
    }
}
