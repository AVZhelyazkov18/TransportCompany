package org.informatics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.informatics.enums.Qualification;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@AllArgsConstructor
public class DriverDto {
    final String firstName;
    final String lastName;
    final String email;
    final BigDecimal salary;
    final Set<Qualification> qualifications;
}
