package com.example.paymentappapple.controller;

import com.example.paymentappapple.dto.DetailDto;
import com.example.paymentappapple.response.ApiResponse;
import com.example.paymentappapple.service.DetailService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detail")
public class DetailController {

    final
    DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @PostMapping
    public HttpEntity<?> addDetail(@RequestBody DetailDto detailDto) {
        ApiResponse apiResponse = detailService.add(detailDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = detailService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> getList() {
        ApiResponse apiResponse = detailService.getAll();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateById(@PathVariable Long id, @RequestBody DetailDto detailDto) {
        ApiResponse apiResponse = detailService.update(id, detailDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = detailService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
