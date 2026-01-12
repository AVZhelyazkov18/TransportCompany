package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.informatics.enums.TransportType;
import org.informatics.exceptions.IllegalCargoException;
import org.informatics.exceptions.RequiredCargoException;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportType transportType;

    @NotBlank
    @Column(nullable = false)
    private String startLocation;

    @NotBlank
    @Column(nullable = false)
    private String endLocation;

    @NotNull
    @Column(nullable = false)
    private LocalDate departureDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate arrivalDate;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private Double amount;

    private boolean isPaid;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private Worker approver;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Order(TransportType transportType, String startLocation, String endLocation, LocalDate departureDate, LocalDate arrivalDate, Double amount, Client client, Company company) {
        this.transportType = transportType;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.amount = amount;
        this.isPaid = false;
        this.client = client;
        this.company = company;
    }
}