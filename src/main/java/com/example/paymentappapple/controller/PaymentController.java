package com.example.paymentappapple.controller;

import com.example.paymentappapple.dto.PaymentDto;
import com.example.paymentappapple.response.ApiResponse;
import com.example.paymentappapple.service.PaymentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    final
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping
    public HttpEntity<?> addPayment(@RequestBody PaymentDto paymentDto) {
        ApiResponse apiResponse = paymentService.add(paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = paymentService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> getAllProduct() {
        ApiResponse apiResponse = paymentService.getAll();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateById(@PathVariable Long id, @RequestBody PaymentDto paymentDto) {
        ApiResponse apiResponse = paymentService.update(id, paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = paymentService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
