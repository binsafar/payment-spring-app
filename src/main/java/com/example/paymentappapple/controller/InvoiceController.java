package com.example.paymentappapple.controller;

import com.example.paymentappapple.dto.DetailDto;
import com.example.paymentappapple.dto.InvoiceDto;
import com.example.paymentappapple.response.ApiResponse;
import com.example.paymentappapple.service.InvoiceService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    final
    InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody InvoiceDto invoiceDto) {
        ApiResponse apiResponse = invoiceService.add(invoiceDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse apiResponse = invoiceService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> getList() {
        ApiResponse apiResponse = invoiceService.getAll();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateById(@PathVariable Long id, @RequestBody InvoiceDto invoiceDto) {
        ApiResponse apiResponse = invoiceService.update(id, invoiceDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = invoiceService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
