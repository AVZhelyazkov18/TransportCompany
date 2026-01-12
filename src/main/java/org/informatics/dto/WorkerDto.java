package org.informatics.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class WorkerDto {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final BigDecimal salary;
}
