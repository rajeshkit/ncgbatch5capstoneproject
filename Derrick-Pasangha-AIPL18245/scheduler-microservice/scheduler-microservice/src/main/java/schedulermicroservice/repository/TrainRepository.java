package schedulermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulermicroservice.model.Train;

public interface TrainRepository extends JpaRepository<Train,Integer> {
}
