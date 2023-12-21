package Service;

import Controller.TrainController;
import Model.Train;
import org.junit.jupiter.api.BeforeEach;
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

@WebMvcTest(TrainController.class)
public class TrainServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TrainService trainService;

    @InjectMocks
    private TrainController trainController;

    @BeforeEach
    public void setUp() {
        Train train = new Train();
        train.setTrainId(1L);
        train.setTrainName("Express");
        train.setTotalKms(100);
        when(trainService.getTrainDetailsById(1L)).thenReturn(train);
    }

    @Test
    public void testGetTrainDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trains/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainName").value("Express"))
                .andExpect(jsonPath("$.totalKms").value(100));}
}
