package com.altimetrik.schedulemicroservice.model;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleTest {


    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void scheduleWithValidData() {
        Schedule schedule = Schedule.builder()
                .scheduleId("S001")
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now().plusHours(2))
                .routeId("R001")
                .trainNumber("T001")
                .build();

        assertThat(validator.validate(schedule)).isEmpty();
    }
}
