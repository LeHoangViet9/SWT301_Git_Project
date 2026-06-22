package com.edu.fucarrentingsystem.repository;

import com.edu.fucarrentingsystem.model.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByMobile( String mobile);

    boolean existsByEmail(String email);

    boolean existsByIdentityCard(String identityCard);

    boolean existsByLicenceNumber(String licenceNumber);

    Optional<Customer> findByEmail( String email);

    Optional<Customer> findByMobile( String mobile);

    Optional<Customer> findByIdentityCard( String identityCard);


    Optional<Customer> findByLiCustomercenceNumber(String licenceNumber);
}
