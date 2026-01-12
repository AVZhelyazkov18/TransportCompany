package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.informatics.enums.Qualification;

@Getter
@Setter
@Entity
@Table(name = "workers")
@NoArgsConstructor
public class Worker extends BaseEntity {
    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private BigDecimal salary;

    @ElementCollection(targetClass = Qualification.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "worker_qualifications",
            joinColumns = @JoinColumn(name = "worker_id")
    )
    @Column(name = "qualification")
    private Set<Qualification> qualifications;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Worker(String firstName, String lastName, String email, BigDecimal salary, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.salary = salary;
    }
}