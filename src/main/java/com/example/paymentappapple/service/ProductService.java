package com.example.paymentappapple.service;

import com.example.paymentappapple.dto.ProductDto;
import com.example.paymentappapple.entity.Attachment;
import com.example.paymentappapple.entity.Category;
import com.example.paymentappapple.entity.Product;
import com.example.paymentappapple.repository.AttachmentRepository;
import com.example.paymentappapple.repository.CategoryRepository;
import com.example.paymentappapple.repository.ProductRepository;
import com.example.paymentappapple.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    final
    ProductRepository productRepository;

    final
    AttachmentRepository attachmentRepository;

    final
    CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, AttachmentRepository attachmentRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.attachmentRepository = attachmentRepository;
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse add(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        Optional<Attachment> byId = attachmentRepository.findById(productDto.getAttachmentId());
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        product.setAttachment(byId.get());
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        if (category.isEmpty()) return new ApiResponse("Failed", false);
        product.setCategory(category.get());
        productRepository.save(product);
        return new ApiResponse("Success", true, product.getId());
    }

    public ApiResponse getOne(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        return new ApiResponse("Success", true, byId.get());
    }

    public ApiResponse getAll() {
        List<Product> all = productRepository.findAll();
        if (all.isEmpty()) return new ApiResponse("Product empty", true);
        return new ApiResponse("Success", true, all);
    }


    public ApiResponse update(Long id, ProductDto productDto) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        Product product = byId.get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(productDto.getAttachmentId());
        if (attachmentOptional.isEmpty()) return new ApiResponse("Failed", false);
        product.setAttachment(attachmentOptional.get());
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        if (category.isEmpty()) return new ApiResponse("Failed", false);
        product.setCategory(category.get());
        productRepository.save(product);
        return new ApiResponse("Success", true, product.getId());

    }

    public ApiResponse delete(Long id) {
        boolean existsById = productRepository.existsById(id);
        Optional<Product> byId = productRepository.findById(id);
        if (existsById) {
            File file = new File("files\\" + byId.get().getAttachment().getOriginalName());
            file.delete();
            attachmentRepository.deleteById(byId.get().getAttachment().getId());
            productRepository.deleteById(id);

            return new ApiResponse("Success", true);
        } else {
            return new ApiResponse("Failed", false);
        }
    }
}
