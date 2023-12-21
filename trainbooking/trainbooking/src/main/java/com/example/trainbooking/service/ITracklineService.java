package com.example.trainbooking.service;

import com.example.trainbooking.requestdto.TracklineDto;
import com.example.trainbooking.responsedto.TracklineResponse;

public interface ITracklineService {
    String updateTrackline(int id, TracklineDto tracklineDTO);
    TracklineResponse getAirlineById(int id);
    String registerTrackline(TracklineDto tracklineDTO);

    TracklineResponse getTracklineById(int id);
}
