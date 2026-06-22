package com.edu.fucarrentingsystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private String identityCard;

    @Column(nullable = false)
    private String licenceNumber;

    @Column(nullable = false)
    private LocalDate licenceDate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "AccountID", nullable = false)
    private Account account;
}
