package com.edu.fucarrentingsystem.service.impl;

import com.edu.fucarrentingsystem.model.dto.customer.request.CustomerRequest;
import com.edu.fucarrentingsystem.model.dto.customer.response.CustomerResponse;
import com.edu.fucarrentingsystem.model.entity.Customer;
import com.edu.fucarrentingsystem.repository.CustomerRepository;
import com.edu.fucarrentingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {

        if (customerRepository.existsByEmail(customerRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (customerRepository.existsByMobile(customerRequest.getMobile())) {
            throw new IllegalArgumentException("Mobile number already exists");
        }

        if (customerRepository.existsByIdentityCard(customerRequest.getIdentityCard())) {
            throw new IllegalArgumentException("Identity card already exists");
        }

        if (customerRepository.existsByLicenceNumber(customerRequest.getLicenceNumber())) {
            throw new IllegalArgumentException("Licence number already exists");
        }

        Customer customer = new Customer();
        mapRequestToCustomer(customerRequest, customer);

        Customer savedCustomer = customerRepository.save(customer);

        return convertToResponse(savedCustomer);
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customerRepository.findByEmail(customerRequest.getEmail())
                .ifPresent(c -> {
                    if (!c.getCustomerID().equals(id)) {
                        throw new IllegalArgumentException("Email already exists");
                    }
                });

        customerRepository.findByMobile(customerRequest.getMobile())
                .ifPresent((Customer c) -> {
                    if(!c.getCustomerID().equals(id)) throw new IllegalArgumentException("Mobile number already exists");
                });

        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setMobile(customerRequest.getMobile());
        customer.setEmail(customerRequest.getEmail());
        customer.setBirthday(customerRequest.getBirthday());
        customer.setIdentityCard(customerRequest.getIdentityCard());
        customer.setLicenceNumber(customerRequest.getLicenceNumber());
        customer.setLicenceDate(customerRequest.getLicenceDate());

        if (customer.getAccount() != null) {
            customer.getAccount().setAccountName(customerRequest.getEmail());
        }

        Customer savedCustomer = customerRepository.save(customer);
        return convertToResponse(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer=customerRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Customer not found"));
        customerRepository.delete(customer);
    }

    private CustomerResponse convertToResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getCustomerID())
                .customerName(customer.getCustomerName())
                .mobile(customer.getMobile())
                .email(customer.getEmail())
                .birthday(customer.getBirthday())
                .identityCard(customer.getIdentityCard())
                .licenceNumber(customer.getLicenceNumber())
                .licenceDate(customer.getLicenceDate())
                .build();
    }
}
