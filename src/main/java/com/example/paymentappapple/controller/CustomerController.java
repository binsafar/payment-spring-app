package com.example.paymentappapple.controller;

import com.example.paymentappapple.dto.CustomerDto;
import com.example.paymentappapple.response.ApiResponse;
import com.example.paymentappapple.service.CustomerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    final
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public HttpEntity<?> addCustomer(@RequestBody CustomerDto customerDto) {
        ApiResponse apiResponse = customerService.add(customerDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = customerService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> getList() {
        ApiResponse apiResponse = customerService.getAll();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/_without_orders")
    public HttpEntity<?> getWithoutOrders() {
        ApiResponse apiResponse = customerService.customerWithoutOrders();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateById(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        ApiResponse apiResponse = customerService.update(id, customerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = customerService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}
