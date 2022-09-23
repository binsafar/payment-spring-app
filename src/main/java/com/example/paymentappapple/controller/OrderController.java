package com.example.paymentappapple.controller;

import com.example.paymentappapple.dto.OrderDto;
import com.example.paymentappapple.response.ApiResponse;
import com.example.paymentappapple.service.OrderService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    final
    OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public HttpEntity<?> add(@RequestBody OrderDto orderDto) {
        ApiResponse apiResponse = orderService.add(orderDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = orderService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> getList() {
        ApiResponse apiResponse = orderService.getAll();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateById(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        ApiResponse apiResponse = orderService.update(id, orderDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = orderService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
