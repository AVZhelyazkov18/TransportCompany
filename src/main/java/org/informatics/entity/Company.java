package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter
public class Company extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private LocalDate registrationDate;

    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = true)
    private Worker owner;

    @ManyToMany
    @JoinTable(
            name = "company_partners",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id")
    )
    private List<Worker> partners;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Worker> workers;

    public Company(String name, LocalDate registrationDate, Worker owner) {
        this.name = name;
        this.registrationDate = registrationDate;
        this.owner = owner;
    }

    public Company(String name, LocalDate registrationDate) {
        this.name = name;
        this.registrationDate = registrationDate;
    }
}