package com.altimetrik.trainmicroservice.Controller;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//To Test the methods of TrainController class.
@WebMvcTest(TrainController.class)

class TrainControllerTest {

    /*Before executing the controller method we have to test it.
    For that we need to invoke request mapping
   So to invoke request mapping we create/inject MockMvc object*/
    @Autowired
    private MockMvc mockMvc ;
    @MockBean
    private TrainService trainService;

//   Used to bind JSON data to java Object
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addTrainDetail() throws Exception
    {
        Train t1 = Train.builder()
                .trainNumber(19208).trainName("Kota-Hisar Superfast").totalKm(350)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainService.addTrainDetail(t1)).thenReturn(t1);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t1)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.trainNumber").value(19208));
    }

    @Test
    void getTrainByNumber() throws Exception {
        Train t1 = Train.builder()
                .trainNumber(19208).trainName("Kota-Hisar Superfast").totalKm(350)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainService.getTrainByNumber(19208)).thenReturn(t1);

        mockMvc.perform(get("/train-api/train/19208")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t1)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.trainNumber").value(19208));

    }

    // ERROR
    @Test
    void updateTrainByNumber() throws Exception {
        Train t1 = Train.builder()
                .trainNumber(19208).trainName("Kota-Hisar Superfast").totalKm(350)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();
        Train t2 = Train.builder()
                .trainNumber(19208).trainName("Kota-Hisar Express").totalKm(350)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainService.updateTrainByNumber(t1)).thenReturn(t2);
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t1)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.trainName").value("Kota-Hisar Express"));
    }

    @Test
    void getAllTrainDetails() throws Exception
    {
        Train t1 = Train.builder()
                .trainNumber(19208).trainName("Kota-Hisar Superfast").totalKm(350)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Train t2 = Train.builder()
                .trainNumber(18121).trainName("Rajdhani Express").totalKm(1000)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainService.getAllTrainDetails()).thenReturn(Arrays.asList(t1,t2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void deleteTrainByNumber() throws Exception
    {
        int trainId = 1;
        Mockito.when(trainService.deleteTrainByNumber(trainId)).thenReturn("Train deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/train-api/train/{id}", trainId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Train deleted successfully"));
    }


}
