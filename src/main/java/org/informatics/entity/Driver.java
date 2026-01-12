package org.informatics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.informatics.enums.Qualification;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Driver extends Worker {
    @ElementCollection(targetClass = Qualification.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "worker_qualifications",
            joinColumns = @JoinColumn(name = "worker_id")
    )
    @Column(name = "qualification", nullable = false)
    private Set<Qualification> qualifications;

    @OneToMany(mappedBy = "driver")
    private Set<Order> orders;

    public Driver(String firstName, String lastName, String email, BigDecimal salary, Company company, Set<Qualification> qualifications) {
        super(firstName, lastName, email, salary, company);
        this.qualifications = qualifications;
    }
}