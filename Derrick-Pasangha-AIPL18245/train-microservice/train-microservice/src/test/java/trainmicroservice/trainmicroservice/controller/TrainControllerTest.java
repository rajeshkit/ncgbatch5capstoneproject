package trainmicroservice.trainmicroservice.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import trainmicroservice.trainmicroservice.model.Train;
import trainmicroservice.trainmicroservice.service.TrainService;

import java.util.Arrays;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainController.class)
class TestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrainService trainService;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void addTrain() throws Exception {
        Train p1 = Train.builder()
                .trainNumber(100).trainName("lalbagh").totalKms(300).acCoaches(2).generalCoaches(2).acCoachTotalSeats(100).generalCoachesTotalSeats(100).sleeperCoaches(2).sleeperCoachTotalSeats(100).build();
        Mockito.when(trainService.addTrain(p1)).thenReturn(p1);
        mockMvc.perform((RequestBuilder) get("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.train_number").value(100));

    }

    @Test
    void getAllTrains() throws Exception {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("lalbagh").totalKms(300).acCoaches(2).generalCoaches(2).acCoachTotalSeats(100).generalCoachesTotalSeats(100).sleeperCoaches(2).sleeperCoachTotalSeats(100).build();
        Train t2 = Train.builder()
                .trainNumber(101).trainName("brindavan").totalKms(300).acCoaches(2).generalCoaches(2).acCoachTotalSeats(100).generalCoachesTotalSeats(100).sleeperCoaches(2).sleeperCoachTotalSeats(100).build();
        Mockito.when(trainService.getAllTrains()).thenReturn(Arrays.asList(t1,t2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainById() {
    }

    @Test
    void updateTrain() {
    }

    @Test
    void deleteTrainById() {
    }

}