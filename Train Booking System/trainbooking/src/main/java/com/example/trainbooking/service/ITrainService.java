package com.example.trainbooking.service;

import com.example.trainbooking.entity.TrainDetails;
import com.example.trainbooking.exception.TrainIdNotFoundException;
import com.example.trainbooking.exception.TrainNotFoundException;
import com.example.trainbooking.requestdto.TrackRequest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ITrainService {

    String trainRegistration(TrackRequest trackRequest);

    List<TrainDetails> searchBySourceAndDestinationAndDepartureDate(String source, String destination, Date date) throws TrainNotFoundException;

    Optional<TrainDetails> getTrainById(int id) throws TrainIdNotFoundException;
}
