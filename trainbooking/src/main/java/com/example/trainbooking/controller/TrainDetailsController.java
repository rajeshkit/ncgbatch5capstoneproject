package com.example.trainbooking.controller;
import com.example.trainbooking.entity.TrainDetails;
import com.example.trainbooking.exception.EmptyInputException;
import com.example.trainbooking.exception.NoRecordFoundException;
import com.example.trainbooking.requestdto.TrackRequest;
import com.example.trainbooking.responsedto.TrainResponse;
import com.example.trainbooking.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/train-api")
public class TrainDetailsController
{
    private final ITrainService trainService;

    @Autowired
    public TrainDetailsController(ITrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping("/register")
    public String trainRegister(@RequestBody TrackRequest trackRequest)
    {
        if(trackRequest == null)
        {
            throw new EmptyInputException("Input is Empty , Please Enter Valid Input");
        }
        return trainService.trainRegistration(trackRequest);
    }

    @GetMapping("/findBy-source-destination-departureDate")
    public List<TrainResponse> searchBySourceAndDestinationAndDepartureDate(@RequestParam(name = "source") String source , @RequestParam(name = "destination") String destination , @RequestParam(name = "departureDate")Date date)
    {
        if(destination == null || destination.isEmpty())
            throw new EmptyInputException("Please Enter Destination");

        if(source == null || source.isEmpty())
            throw new EmptyInputException("Please Enter Source");

        List<TrainDetails> trainDetailsList = trainService.searchBySourceAndDestinationAndDepartureDate(source,destination,date);
        List<TrainResponse> list = new ArrayList<>();

        if(trainDetailsList.isEmpty())
        {
            throw new NoRecordFoundException("No train Data Exists");
        }

        for(TrainDetails trainDetails : trainDetailsList)
        {
            int totalSeats = trainDetails.getTotalSeats();

            String src = trainDetails.getSource();

            String des = trainDetails.getDestination();

            String departureTime = trainDetails.getDepartureTime();

            int fare = trainDetails.getFare();

            int availableSeats = trainDetails.getAvailableSeats();

            Date departureDate = trainDetails.getDepartureDate();

            String  tracklineName = trainDetails.getTrain().getName();

            Date dateOfOperation = trainDetails.getTrain().getDateOfOperation();

            list.add(new TrainResponse(totalSeats,src,des,departureTime,fare,availableSeats,departureDate,tracklineName,dateOfOperation));
        }
        return list;
    }

    @GetMapping("/findBy-id")
    public TrainResponse getTrainById(@RequestParam("id") int id)
    {
        Optional<TrainDetails> trainObject = trainService.getTrainById(id);

        if(trainObject.isEmpty())
        {
            throw new NoRecordFoundException("No train Data exists for given input");
        }

        TrainDetails trainDetails = trainObject.get();

        int totalSeats = trainDetails.getTotalSeats();

        String src = trainDetails.getSource();

        String des = trainDetails.getDestination();

        String departureTime = trainDetails.getDepartureTime();

        int fare = trainDetails.getFare();

        int availableSeats = trainDetails.getAvailableSeats();

        Date departureDate = trainDetails.getDepartureDate();

        String  tracklineName = trainDetails.getTrain().getName();

        Date dateOfOperation = trainDetails.getTrain().getDateOfOperation();

        return new TrainResponse(totalSeats,src,des,departureTime,fare,availableSeats,departureDate,tracklineName,dateOfOperation);
    }
}
