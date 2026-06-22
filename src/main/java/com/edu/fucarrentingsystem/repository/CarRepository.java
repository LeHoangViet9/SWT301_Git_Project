package com.edu.fucarrentingsystem.repository;

import com.edu.fucarrentingsystem.model.entity.Car;
import com.edu.fucarrentingsystem.model.entity.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByStatus(CarStatus status);

    List<Car> findByCarNameContainingIgnoreCase(String carName);

    List<Car> findByCarModelYear(Integer carModelYear);
}