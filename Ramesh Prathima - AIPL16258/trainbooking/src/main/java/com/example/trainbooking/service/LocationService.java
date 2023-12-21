package com.example.trainbooking.service;

import com.example.trainbooking.entity.Location;
import com.example.trainbooking.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}