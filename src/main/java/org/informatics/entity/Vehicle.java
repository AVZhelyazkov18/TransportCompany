package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.informatics.enums.VehicleType;

@Entity
@Table(name = "vehicles")
@NoArgsConstructor
@Getter
public class Vehicle extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Vehicle(VehicleType type, String registrationNumber, Company company) {
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.company = company;
    }
}