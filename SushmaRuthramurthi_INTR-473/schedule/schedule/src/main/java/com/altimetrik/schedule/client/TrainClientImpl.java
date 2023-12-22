package com.altimetrik.schedule.client;

import com.altimetrik.schedule.Dto.Train;
import com.altimetrik.schedule.exception.InvalidTrainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class TrainClientImpl implements TrainClient{
    @Override
    public Train getTrain(int trainNumber) throws InvalidTrainException {
        RestTemplate restTemplate = new RestTemplate();
        String trainResourceUrl = "http://localhost:8080/api/v1/train/"+trainNumber+"?includecoaches=true";
        ResponseEntity<Train> response = restTemplate.getForEntity(trainResourceUrl, Train.class);
        if(response.getStatusCode() == HttpStatus.OK)
        {
            return response.getBody();
        }
        else
            throw new InvalidTrainException("Invalid Train Number:"+trainNumber);
    }

}

