package com.edu.fucarrentingsystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "CarID", nullable = false)
    private Car car;

    @Column(nullable = false)
    private Integer reviewStar;

    @Column(nullable = false)
    private String comment;
}
