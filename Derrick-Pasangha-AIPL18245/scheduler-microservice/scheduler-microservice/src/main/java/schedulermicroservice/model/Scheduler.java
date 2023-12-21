package schedulermicroservice.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Validated
public class Scheduler {
    @Id
    int scheduleId;
    private Timestamp departureDateTime;
    private Timestamp arrivalDateTime;
    private String train;
    private String route;
}
