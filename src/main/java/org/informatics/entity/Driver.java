package org.informatics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.informatics.enums.Qualification;

import java.util.Set;

@Entity
@Getter
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
}