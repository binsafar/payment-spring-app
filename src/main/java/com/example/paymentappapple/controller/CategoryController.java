package com.example.paymentappapple.controller;

import com.example.paymentappapple.dto.CategoryDto;
import com.example.paymentappapple.response.ApiResponse;
import com.example.paymentappapple.service.CategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    final
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @PostMapping
    public HttpEntity<?> addCategory(@RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.add(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> getCategoryList() {
        ApiResponse apiResponse = categoryService.getList();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 4009).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getByCategoryId(@PathVariable Long id) {
        ApiResponse apiResponse = categoryService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateById(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.update(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }
}
