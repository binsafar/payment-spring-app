package com.example.paymentappapple.service;

import com.example.paymentappapple.dto.OrderDto;
import com.example.paymentappapple.entity.Customer;
import com.example.paymentappapple.entity.Order;
import com.example.paymentappapple.repository.CustomerRepository;
import com.example.paymentappapple.repository.OrderRepository;
import com.example.paymentappapple.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    final
    OrderRepository orderRepository;

    final
    CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public ApiResponse add(OrderDto orderDto) {
        Order order = new Order();

        order.setDate(orderDto.getDate());
        Optional<Customer> byId = customerRepository.findById(orderDto.getCustomerId());
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        order.setCustomer(byId.get());

        orderRepository.save(order);
        return new ApiResponse("Success", true, order.getId());
    }

    public ApiResponse getOne(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        return new ApiResponse("Success", true, byId.get());
    }

    public ApiResponse getAll() {
        List<Order> all = orderRepository.findAll();
        if (all.isEmpty()) return new ApiResponse("Empty", true);
        return new ApiResponse("Success", true, all);
    }

    public ApiResponse update(Long id, OrderDto orderDto) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isEmpty()) return new ApiResponse("Failed", false);
        Order order = optional.get();

        order.setDate(orderDto.getDate());
        Optional<Customer> byId = customerRepository.findById(orderDto.getCustomerId());
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        order.setCustomer(byId.get());

        orderRepository.save(order);
        return new ApiResponse("Success", true, order.getId());

    }

    public ApiResponse delete(Long id) {
        boolean b = orderRepository.existsById(id);
        if (b) {
            orderRepository.deleteById(id);
            return new ApiResponse("Success", true);
        }else {
            return new ApiResponse("Failed", false);
        }

    }


}
