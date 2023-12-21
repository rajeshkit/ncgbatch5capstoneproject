package com.schedulemicroservice.repository;
import com.schedulemicroservice.model.ScheduleRequest;
import org.springframework.data.jpa.repository.JpaRepository;




public interface ScheduleRequestRepository extends JpaRepository<ScheduleRequest,Integer> {

}
