package com.altimetrik.routemicroservice.model;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteTests {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    void shouldCreateValidRoute() {
        // Given
        Route route = Route.builder()
                .routeId("R0001")
                .source("SourceCity")
                .destination("DestinationCity")
                .totalKms(200)
                .build();

        // When
        var violations = validator.validate(route);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailValidationWhenRouteIdIsEmpty() {
        // Given
        Route route = Route.builder()
                .source("SourceCity")
                .destination("DestinationCity")
                .totalKms(200)
                .build();

        // When
        var violations = validator.validate(route);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Please enter a valid Route ID");
    }

    @Test
    void shouldFailValidationWhenSourceIsEmpty() {
        // Given
        Route route = Route.builder()
                .routeId("R0001")
                .destination("DestinationCity")
                .totalKms(200)
                .build();

        // When
        var violations = validator.validate(route);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Source cannot be empty");
    }

    @Test
    void shouldFailValidationWhenDestinationIsEmpty() {
        // Given
        Route route = Route.builder()
                .routeId("R0001")
                .source("SourceCity")
                .totalKms(200)
                .build();

        // When
        var violations = validator.validate(route);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Destination cannot be empty");
    }

    @Test
    void shouldFailValidationWhenTotalKmsIsNegative() {
        // Given
        Route route = Route.builder()
                .routeId("R0001")
                .source("SourceCity")
                .destination("DestinationCity")
                .totalKms(-200)
                .build();

        // When
        var violations = validator.validate(route);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Total kilometers must be Greater then Zero");
    }


}
