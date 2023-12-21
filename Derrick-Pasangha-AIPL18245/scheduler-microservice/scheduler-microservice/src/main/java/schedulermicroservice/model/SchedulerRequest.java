package schedulermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Validated
public class SchedulerRequest {
    @Id
    @GeneratedValue
    private int scheduleId;
    @FutureOrPresent(message = "Enter the date in yyyy/mm/dd hh/mm/ss format")
    private Timestamp departureDateTime;

    @FutureOrPresent(message = "Enter the date in yyyy/mm/dd hh/mm/ss format")
    private Timestamp arrivalDateTime;

    private int trainNo;
    private int routeId;


}
