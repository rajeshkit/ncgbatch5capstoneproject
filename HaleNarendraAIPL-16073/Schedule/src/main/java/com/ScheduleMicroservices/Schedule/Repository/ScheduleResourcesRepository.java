package com.ScheduleMicroservices.Schedule.Repository;


import com.ScheduleMicroservices.Schedule.model.NewScheduleResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleResourcesRepository extends JpaRepository<NewScheduleResources,Long>{


    List<NewScheduleResources> findByTrainResources_trainNumber(Long trainId);
}
