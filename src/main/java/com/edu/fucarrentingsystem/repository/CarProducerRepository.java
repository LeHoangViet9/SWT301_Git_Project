package com.edu.fucarrentingsystem.repository;

import com.edu.fucarrentingsystem.model.entity.CarProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarProducerRepository extends JpaRepository<CarProducer,Long> {
}
