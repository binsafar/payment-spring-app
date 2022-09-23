package com.example.paymentappapple.service;

import com.example.paymentappapple.dto.CategoryDto;
import com.example.paymentappapple.entity.Category;
import com.example.paymentappapple.repository.CategoryRepository;
import com.example.paymentappapple.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse add(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getName() == null || categoryDto.getName().equals("")) return new ApiResponse("Failed", false);
        categoryRepository.save(category);
        return new ApiResponse("Saved", true, category.getId());
    }

    public ApiResponse getList() {
        List<Category> all = categoryRepository.findAll();
        if (all.isEmpty()) return new ApiResponse("Category is empty", false);
        return new ApiResponse("Success", true, all);
    }

    public ApiResponse getOne(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            return new ApiResponse("Success", true, byId);
        } else {
            return new ApiResponse("Failed", false);
        }
    }

    public ApiResponse update(Long id, CategoryDto categoryDto) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            if (categoryDto.getName() == null || categoryDto.getName().equals(""))
                return new ApiResponse("Failed", false);
            category.setName(categoryDto.getName());
            categoryRepository.save(category);
            return new ApiResponse("Success", true);
        } else {
            return new ApiResponse("Failed", false);
        }
    }

    public ApiResponse delete(Long id) {
        boolean existsById = categoryRepository.existsById(id);
        if (existsById) {
            categoryRepository.deleteById(id);
            return new ApiResponse("Success", true);
        } else {
            return new ApiResponse("Failed", false);
        }

    }
}
