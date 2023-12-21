package com.train.trainmicroservice;

import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.repository.TrainRepository;
import com.train.trainmicroservice.service.TrainService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TrainMicroserviceApplicationTests {
    @Test
    void contextLoads() {
    }



}
