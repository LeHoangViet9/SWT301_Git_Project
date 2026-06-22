package com.edu.fucarrentingsystem.service.impl;

import com.edu.fucarrentingsystem.model.dto.customer.request.CustomerRequest;
import com.edu.fucarrentingsystem.model.dto.customer.response.CustomerResponse;
import com.edu.fucarrentingsystem.model.entity.Customer;
import com.edu.fucarrentingsystem.repository.CustomerRepository;
import com.edu.fucarrentingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToResponse) // Tái sử dụng method map để code ngắn gọn
                .toList();
    }

    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerRequest request) {
        // Validate dữ liệu đầu vào trước khi tạo mới
        if (customerRepository.existsByMobile(request.getMobile())) {
            throw new IllegalArgumentException("Mobile number already exists");
        }
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (customerRepository.existsByIdentityCard(request.getIdentityCard())) {
            throw new IllegalArgumentException("Identity card already exists");
        }
        if (customerRepository.existsByLicenceNumber(request.getLicenceNumber())) {
            throw new IllegalArgumentException("Licence number already exists");
        }

        Customer customer = new Customer();
        updateCustomerFields(customer, request);

        Customer savedCustomer = customerRepository.save(customer);
        return convertToResponse(savedCustomer);
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customerRepository.findByEmail(request.getEmail())
                .filter(c -> !c.getCustomerID().equals(id))
                .ifPresent(c -> { throw new IllegalArgumentException("Email already exists"); });

        customerRepository.findByMobile(request.getMobile())
                .filter(c -> !c.getCustomerID().equals(id))
                .ifPresent(c -> { throw new IllegalArgumentException("Mobile number already exists"); });

        // Cập nhật các trường thông tin
        updateCustomerFields(customer, request);

        if (customer.getAccount() != null) {
            customer.getAccount().setAccountName(request.getEmail());
        }

        Customer savedCustomer = customerRepository.save(customer);
        return convertToResponse(savedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        customerRepository.delete(customer);
    }

    private void updateCustomerFields(Customer customer, CustomerRequest request) {
        customer.setCustomerName(request.getCustomerName());
        customer.setMobile(request.getMobile());
        customer.setEmail(request.getEmail());
        customer.setBirthday(request.getBirthday());
        customer.setIdentityCard(request.getIdentityCard());
        customer.setLicenceNumber(request.getLicenceNumber());
        customer.setLicenceDate(request.getLicenceDate());
    }

    private CustomerResponse convertToResponse(Customer customer) {
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