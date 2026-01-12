package org.informatics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.informatics.enums.TransportType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OrderDto {
    final private Long clientId;
    final private String startLocation;
    final private String endLocation;
    final private LocalDate dateOfDeparture;
    final private LocalDate dateOfArrival;
    final private TransportType transportType;
    final private Double cargoWeight;
    final private boolean paid;
}
