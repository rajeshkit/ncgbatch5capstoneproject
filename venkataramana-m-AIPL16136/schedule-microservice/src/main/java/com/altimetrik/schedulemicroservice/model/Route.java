package com.altimetrik.schedulemicroservice.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
public class Route {
//    @Id
    private int routeId;
    private String source;
    private String destination;
    private Double totalKms;

    @Override
    public String toString() {
        return "Route [" +
                "routeId=" + routeId +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", totalKms=" + totalKms +
                ']';
    }
}
