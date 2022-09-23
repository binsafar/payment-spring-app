package com.example.paymentappapple.service;

import com.example.paymentappapple.dto.CustomerDto;
import com.example.paymentappapple.entity.Customer;
import com.example.paymentappapple.repository.CustomerRepository;
import com.example.paymentappapple.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    final
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ApiResponse add(CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setCountry(customerDto.getCountry());
        customer.setPhone(customerDto.getPhone());
        Customer save = customerRepository.save(customer);
        return new ApiResponse("Success", true, save.getId());
    }

    public ApiResponse getOne(Long id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        return new ApiResponse("Success", true, byId.get());
    }

    public ApiResponse getAll() {
        List<Customer> all = customerRepository.findAll();
        if (all.isEmpty()) return new ApiResponse("Empty", true);
        return new ApiResponse("Success", true, all);
    }

    public ApiResponse update(Long id, CustomerDto customerDto) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        Customer customer = byId.get();

        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setCountry(customerDto.getCountry());
        customer.setPhone(customerDto.getPhone());
        Customer save = customerRepository.save(customer);
        return new ApiResponse("Success", true, save.getId());
    }

    public ApiResponse delete(Long id) {
        boolean b = customerRepository.existsById(id);
        if (b) {
            customerRepository.deleteById(id);
            return new ApiResponse("Success", true);
        } else {
            return new ApiResponse("Failed", false);
        }

    }

    public ApiResponse customerWithoutOrders() {
        return null;
    }
}
