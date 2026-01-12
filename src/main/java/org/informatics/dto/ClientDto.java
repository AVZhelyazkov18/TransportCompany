package org.informatics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientDto {
    private final String firstName;
    private final String midName;
    private final String lastName;
    private final String email;
    private final String phone;
}
