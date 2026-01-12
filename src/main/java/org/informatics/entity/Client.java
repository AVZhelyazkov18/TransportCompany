package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String middleName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Client(String firstName, String middleName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
