package com.edu.fucarrentingsystem.service;

import com.edu.fucarrentingsystem.model.dto.customer.request.CustomerRequest;
import com.edu.fucarrentingsystem.model.dto.customer.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getAllCustomers();
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);
    void deleteCustomer(Long id);
}
