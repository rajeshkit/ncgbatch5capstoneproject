package Controller;

import Model.Train;
import Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trains")
public class TrainController {

    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Train>> getTrainById(@PathVariable Long id) {
        Optional<Train> train = trainService.getTrainById(id);
        return ResponseEntity.ok(train);
    }

    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains() {
        List<Train> trains = trainService.getAllTrains();
        return ResponseEntity.ok(trains);
    }

    @PostMapping
    public ResponseEntity<Train> createTrain(@RequestBody Train train) {
        Train createdTrain = trainService.createTrain(train);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable Long id, @RequestBody Train train) {
        Train updatedTrain = trainService.updateTrain(id, train);
        return ResponseEntity.ok(updatedTrain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Long id) {
        trainService.deleteTrain(id);
        return ResponseEntity.noContent().build();
    }
}
