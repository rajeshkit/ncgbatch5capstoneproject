package com.example.trainbooking.service;
import com.example.trainbooking.entity.Train;
import com.example.trainbooking.entity.TrainDetails;
import com.example.trainbooking.exception.EmptyInputException;
import com.example.trainbooking.exception.TrainIdNotFoundException;
import com.example.trainbooking.repository.TracklineRepository;
import com.example.trainbooking.repository.TrainRepository;
import com.example.trainbooking.requestdto.TrackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainService implements ITrainService
{
    private final TrainRepository trainRepository;
    private final TracklineRepository tracklineRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository, TracklineRepository tracklineRepository) {
        this.trainRepository = trainRepository;
        this.tracklineRepository = tracklineRepository;
    }


    public String trainRegistration(TrackRequest trackRequest) {
        TrainDetails trainDetails;

        Optional<Train> trackline = tracklineRepository.findByName(trackRequest.getTracklineName());

        if(trackline.isEmpty())
        {
           throw new EmptyInputException("Enter valid Input");
        }
        else {
            trainDetails = new TrainDetails(trackRequest.getTotalSeats(), trackRequest.getSource(), trackRequest.getDestination(), trackRequest.getDepartureTime(), trackRequest.getFare(), trackRequest.getAvailableSeats(), trackRequest.getDepartureDate() ,trackline.get());
        }

        trackline.get().addFlight(trainDetails);

        tracklineRepository.save(trackline.get());
        return "Train Details saved successfully";
    }

    @Override


    public List<TrainDetails> searchBySourceAndDestinationAndDepartureDate(String source , String destination , Date date)
    {
        return trainRepository.findBySourceAndDestinationAndDepartureDate(source,destination,date);
    }

    @Override
    public Optional<TrainDetails> getTrainById(int id) throws TrainIdNotFoundException {
        return Optional.empty();
    }

    public Optional<TrainDetails> getFlightById(int id) {

        return trainRepository.findById(id);
    }
}