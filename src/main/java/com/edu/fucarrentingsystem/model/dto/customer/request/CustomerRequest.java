package com.edu.fucarrentingsystem.model.dto.customer.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Customer name is required")
    private String customerName;
    @NotBlank(message = "Mobile is required")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid mobile format")
    private String mobile;
    @NotNull(message = "Birthday is required")
    private LocalDate birthday;
    @NotBlank(message = "Identity card is required")
    private String identityCard;
    @NotBlank(message = "Licence number is required")
    private String licenceNumber;
    @NotNull(message = "Licence date is required")
    private LocalDate licenceDate;
}
