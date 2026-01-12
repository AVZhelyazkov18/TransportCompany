package org.informatics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CompanyDto {
    final String name;
    @Setter
    Long ownerId;
    final LocalDate registeredOnDate;
}
