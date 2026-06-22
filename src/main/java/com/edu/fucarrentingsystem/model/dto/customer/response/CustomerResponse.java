package com.edu.fucarrentingsystem.model.dto.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private Long id;
    private String email;

    private String customerName;
    private String mobile;
    private LocalDate birthday;
    private String identityCard;
    private String licenceNumber;
    private LocalDate licenceDate;
}
