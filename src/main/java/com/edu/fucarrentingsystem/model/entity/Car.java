package com.edu.fucarrentingsystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carID;

    @Column(nullable = false)
    private String carName;

    @Column(nullable = false)
    private Integer carModelYear;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate importDate;

    @Column(nullable = false)
    private BigDecimal rentPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus status;

    @ManyToOne
    @JoinColumn(name = "ProducerID", nullable = false)
    private CarProducer carProducer;
}
