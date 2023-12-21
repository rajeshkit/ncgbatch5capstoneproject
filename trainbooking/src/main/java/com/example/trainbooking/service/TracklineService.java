package com.example.trainbooking.service;

import com.example.trainbooking.entity.Train;
import com.example.trainbooking.repository.TracklineRepository;
import com.example.trainbooking.requestdto.TracklineDto;
import com.example.trainbooking.responsedto.TracklineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TracklineService implements ITracklineService {
    private final TracklineRepository tracklineRepository;

    @Autowired
    public TracklineService(TracklineRepository tracklineRepository) {
        this.tracklineRepository = tracklineRepository;
    }

        public String registerTrackline(TracklineDto tracklineDTO)
        {
            Train train = new Train(tracklineDTO.getTracklineName(), tracklineDTO.getDateOfOperation());
            tracklineRepository.save(train);
            return "Trackline Added Successfully";
        }

        public TracklineResponse getTracklineById(int id)
        {
            Train train = tracklineRepository.findById(id).get();
            return new TracklineResponse(train.getName(), train.getDateOfOperation());
        }

        public String updateTrackline(int id, TracklineDto tracklineDTO) {

            Train train = tracklineRepository.findById(id).get();
            train.setName(tracklineDTO.getTracklineName());
            train.setDateOfOperation(tracklineDTO.getDateOfOperation());
            tracklineRepository.save(train);
            return "Trackline details were updated";
        }

    @Override
    public TracklineResponse getAirlineById(int id) {
        return null;
    }


}



