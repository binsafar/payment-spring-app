package com.example.paymentappapple.repository;

import com.example.paymentappapple.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
