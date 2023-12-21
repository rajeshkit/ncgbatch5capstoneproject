package schedulermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulermicroservice.model.Scheduler;

public interface SchedulerRepository extends JpaRepository<Scheduler,Integer> {
}
