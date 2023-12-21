package com.booking_details.schedule.repository;

import com.booking_details.schedule.model.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 public interface ScheduleRepository extends JpaRepository<ScheduleModel,Long> {
        ScheduleModel findByTrain_TrainNumber(long trainNumber);

}
