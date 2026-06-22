package com.edu.fucarrentingsystem.repository;

import com.edu.fucarrentingsystem.model.entity.Account;
import com.edu.fucarrentingsystem.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountName(String accountName);

    List<Account> findByRole(Role role);

    boolean existsByAccountName(String accountName);

    boolean existsByRole(Role role);
}