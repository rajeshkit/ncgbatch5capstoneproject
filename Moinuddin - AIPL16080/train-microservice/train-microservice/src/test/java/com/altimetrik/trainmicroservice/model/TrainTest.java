package com.altimetrik.trainmicroservice.model;

import com.altimetrik.trainmicroservice.repository.TrainRepository;
import com.altimetrik.trainmicroservice.service.TrainService;
import com.altimetrik.trainmicroservice.service.TrainServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.validation.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainTest {

    @Mock
    private TrainRepository trainRepository;
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @InjectMocks
    private TrainService trainService = new TrainServiceImplementation();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTrainWithValidData() {
        // Given
        Train train = Train.builder()
                .trainNumber("T1234")
                .trainName("Express")
                .totalDistance(500)
                .numberOfACCoaches(2)
                .acCoachTotalSeats(50)
                .numberOfSleeperCoaches(3)
                .sleeperCoachTotalSeats(120)
                .numberOfGeneralCoaches(4)
                .generalCoachTotalSeats(200)
                .build();

        // Mocking the save method of the repository
        when(trainRepository.save(train)).thenReturn(train);

        // When
        Train result = trainService.addTrain(train);

        // Then
        assertEquals(train, result);
        verify(trainRepository, times(1)).save(train);
    }
    @Test
    void testInvalidTrainNumberAndNullTrainName() {
        // Given
        Train train = Train.builder()
                .trainNumber("T12") // Invalid trainNumber
                .trainName(null) // Null trainName
                .totalDistance(500)
                .build();

        // When
        Set<ConstraintViolation<Train>> violations = validator.validate(train);

        // Then
        assertEquals(2, violations.size());
        for (ConstraintViolation<Train> violation : violations) {
            if (violation.getPropertyPath().toString().equals("trainNumber")) {
                assertEquals("Train Number must be exactly 5 characters", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("trainName")) {
                assertEquals("Train Name cannot be null", violation.getMessage());
            }
        }
    }
    @Test
    void testNegativeGeneralCoachesAndSeats() {
        // Given
        Train train = Train.builder()
                .trainNumber("T1234")
                .trainName("Express")
                .totalDistance(500)
                .numberOfGeneralCoaches(-1) // Invalid negative number of general coaches
                .generalCoachTotalSeats(-20) // Invalid negative general coach total seats
                .build();

        // When
        Set<ConstraintViolation<Train>> violations = validator.validate(train);

        // Then
        assertEquals(2, violations.size());

        List<String> violationMessages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        assertTrue(violationMessages.contains("Number of general coaches must be a non-negative value"));
        assertTrue(violationMessages.contains("General coach total seats must be a non-negative value"));
    }


}
