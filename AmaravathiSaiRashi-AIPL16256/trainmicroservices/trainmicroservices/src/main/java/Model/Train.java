package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;
    private String trainName;
    private double totalKms;
    private int acCoaches;
    private int acCoachTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachTotalSeats;
    private int generalCoaches;
    private int generalCoachTotalSeats;

    public Train(String trainNumber, String trainName, double totalKms, int acCoaches, int acCoachTotalSeats,
                 int sleeperCoaches, int sleeperCoachTotalSeats, int generalCoaches, int generalCoachTotalSeats) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.totalKms = totalKms;
        this.acCoaches = acCoaches;
        this.acCoachTotalSeats = acCoachTotalSeats;
        this.sleeperCoaches = sleeperCoaches;
        this.sleeperCoachTotalSeats = sleeperCoachTotalSeats;
        this.generalCoaches = generalCoaches;
        this.generalCoachTotalSeats = generalCoachTotalSeats;
    }

    public void setTrainId(long l) {
    }
}
