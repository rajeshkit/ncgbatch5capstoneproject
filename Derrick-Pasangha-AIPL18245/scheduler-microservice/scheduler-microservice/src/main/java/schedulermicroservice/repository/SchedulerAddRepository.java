package schedulermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulermicroservice.model.SchedulerRequest;

public interface SchedulerAddRepository extends JpaRepository<SchedulerRequest,Integer> {
}
