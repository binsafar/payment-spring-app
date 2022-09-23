package com.example.paymentappapple.service;

import com.example.paymentappapple.dto.DetailDto;
import com.example.paymentappapple.entity.Detail;
import com.example.paymentappapple.entity.Order;
import com.example.paymentappapple.entity.Product;
import com.example.paymentappapple.repository.DetailRepository;
import com.example.paymentappapple.repository.OrderRepository;
import com.example.paymentappapple.repository.ProductRepository;
import com.example.paymentappapple.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {
    final
    OrderRepository orderRepository;

    final
    ProductRepository productRepository;
    final
    DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.detailRepository = detailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public ApiResponse add(DetailDto detailDto) {
        Detail detail = new Detail();
        Optional<Order> byId = orderRepository.findById(detailDto.getOrderId());
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        Optional<Product> product = productRepository.findById(detailDto.getProductId());
        if (product.isEmpty()) return new ApiResponse("Failed", false);

        detail.setOrder(byId.get());
        detail.setProduct(product.get());
        detail.setQuantity(detailDto.getQuantity());
        detailRepository.save(detail);
        return new ApiResponse("Success", true, detail.getId());
    }

    public ApiResponse getOne(Long id) {
        Optional<Detail> byId = detailRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        return new ApiResponse("Success", true, byId.get());
    }

    public ApiResponse getAll() {
        List<Detail> all = detailRepository.findAll();
        if (all.isEmpty()) return new ApiResponse("Empty", true);
        return new ApiResponse("Success", true, all);
    }

    public ApiResponse update(Long id, DetailDto detailDto) {
        Optional<Detail> byIdEx = detailRepository.findById(id);
        if (byIdEx.isEmpty()) return new ApiResponse("Failed", false);

        Detail detail = byIdEx.get();
        Optional<Order> byId = orderRepository.findById(detailDto.getOrderId());
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        Optional<Product> product = productRepository.findById(detailDto.getProductId());
        if (product.isEmpty()) return new ApiResponse("Failed", false);

        detail.setOrder(byId.get());
        detail.setProduct(product.get());
        detail.setQuantity(detailDto.getQuantity());
        detailRepository.save(detail);
        return new ApiResponse("Success", true, detail.getId());
    }


    public ApiResponse delete(Long id) {
        Optional<Detail> byId = detailRepository.findById(id);
        if(byId.isEmpty()) return new ApiResponse("Failed", false);
        detailRepository.deleteById(id);
        return new ApiResponse("Success", true);
    }
}
