package trainmicroservice.trainmicroservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import trainmicroservice.trainmicroservice.model.Train;

public interface Trainrepository extends JpaRepository<Train,Integer> {

}
