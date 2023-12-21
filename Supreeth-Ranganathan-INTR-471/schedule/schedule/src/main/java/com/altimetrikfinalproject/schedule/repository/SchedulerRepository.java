package com.altimetrikfinalproject.schedule.repository;

import com.altimetrikfinalproject.schedule.entity.NewScheduleRequest;
import com.altimetrikfinalproject.schedule.entity.RouteResponse;
import com.altimetrikfinalproject.schedule.entity.ScheduleResponse;
import com.altimetrikfinalproject.schedule.entity.TrainResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SchedulerRepository extends JpaRepository<NewScheduleRequest, Integer> {
//    @Query(nativeQuery = true, value = "SELECT * FROM train tr join new_schedule_request nsr on tr.train_number = nsr.train_id where tr.train_number=:train_number")
//    Optional<ScheduleResponse> getScheduleByTrainID(@Param("train_number") int train_number);
//    @Query(nativeQuery = true, value = "SELECT * FROM route r join new_schedule_request nsr on r.route_id = nsr.route_id where r.route_id=:route_id")
//    Optional<ScheduleResponse> getScheduleByRouteID(@Param("route_id") int route_id);
//    Optional<NewScheduleRequest> findByTrainID(@Param("train_id") int train_id);

}
