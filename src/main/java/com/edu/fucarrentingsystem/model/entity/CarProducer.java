package com.edu.fucarrentingsystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car_producers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producerID;

    @Column(nullable = false)
    private String producerName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String country;
}
