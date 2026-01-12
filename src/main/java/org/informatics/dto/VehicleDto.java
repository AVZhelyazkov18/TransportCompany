package org.informatics.dto;

import lombok.Getter;
import org.informatics.enums.VehicleType;

@Getter
public class VehicleDto {
    final private VehicleType type;
    final private String registrationNumber;

    public VehicleDto(VehicleType type, String registrationNumber) {
        this.type = type;
        this.registrationNumber = registrationNumber;
    }
}