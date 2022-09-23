package com.example.paymentappapple.repository;

import com.example.paymentappapple.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
