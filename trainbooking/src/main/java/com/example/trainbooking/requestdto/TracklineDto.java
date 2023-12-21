package com.example.trainbooking.requestdto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.sql.Date;
@Getter
public class TracklineDto {
        @NotEmpty(message = "tracklineName field is Empty")
        private final String tracklineName;

        @NotEmpty
        @NotBlank
        private final Date dateOfOperation;


        public TracklineDto(String airlineName, Date dateOfOperation) {
            this.tracklineName = airlineName;
            this.dateOfOperation = dateOfOperation;
        }

    }
