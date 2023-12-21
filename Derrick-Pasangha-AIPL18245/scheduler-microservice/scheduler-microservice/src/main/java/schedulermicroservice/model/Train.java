package schedulermicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Validated
public class Train {
    @Id
    int trainNumber;
    String trainName;
    int totalKms;
    int acCoaches;
    int acCoachTotalSeats;
    int sleeperCoaches;
    int sleeperCoachTotalSeats;
    int generalCoaches;
    int generalCoachesTotalSeats;
}
