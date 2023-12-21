package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.RouteMatcher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_date_time")
    private LocalDateTime arrivalDateTime;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteMatcher.Route route;

    public Schedule save(Schedule schedule) {
        return schedule;
    }

    public void deleteById(Long id) {
    }

    public Optional<Schedule> findById(Long id) {
        return null;
    }

    public List<Schedule> findAll() {
        return null;
    }

    public long getScheduleId() {
        return 0;
    }

    public String getDeparture() {
        return null;
    }

    public String getArrival() {
        return null;
    }

    public String getDay() {
        return null;
    }

    private class Train {
    }
}
