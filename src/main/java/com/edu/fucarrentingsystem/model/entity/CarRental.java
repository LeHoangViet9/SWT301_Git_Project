package com.edu.fucarrentingsystem.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "car_rentals")
public class CarRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalID;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "CarID", nullable = false)
    private Car car;

    @Column(nullable = false)
    private LocalDate pickupDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(nullable = false)
    private BigDecimal rentPrice;

    @Column(nullable = false)
    private String status;

    
    public long getRentalDays() {
        return returnDate.toEpochDay() - pickupDate.toEpochDay();
    }

    
    public BigDecimal getTotalPrice() {
        long days = getRentalDays();
        return rentPrice.multiply(BigDecimal.valueOf(days));
    }

  
    public boolean isActive() {
        return "RENTING".equalsIgnoreCase(status);
    }
}
