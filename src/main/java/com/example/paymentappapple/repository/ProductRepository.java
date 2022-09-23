package com.example.paymentappapple.repository;

import com.example.paymentappapple.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
