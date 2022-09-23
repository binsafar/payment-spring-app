package com.example.paymentappapple.service;

import com.example.paymentappapple.dto.PaymentDto;
import com.example.paymentappapple.entity.Invoice;
import com.example.paymentappapple.entity.Payment;
import com.example.paymentappapple.repository.InvoiceRepository;
import com.example.paymentappapple.repository.PaymentRepository;
import com.example.paymentappapple.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    final
    PaymentRepository paymentRepository;

    final
    InvoiceRepository invoiceRepository;

    public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }


    public ApiResponse add(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        Optional<Invoice> byId = invoiceRepository.findById(paymentDto.getInvoiceId());
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        payment.setInvoice(byId.get());

        paymentRepository.save(payment);
        return new ApiResponse("Success", true, payment.getId());
    }

    public ApiResponse getOne(Long id) {
        Optional<Payment> byId = paymentRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        return new ApiResponse("Success", true, byId.get());
    }

    public ApiResponse getAll() {
        List<Payment> all = paymentRepository.findAll();
        if (all.isEmpty()) return new ApiResponse("Empty", true);
        return new ApiResponse("Success", true, all);
    }

    public ApiResponse update(Long id, PaymentDto paymentDto) {
        Optional<Payment> byId = paymentRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        Payment payment = byId.get();
        payment.setAmount(paymentDto.getAmount());
        Optional<Invoice> byI = invoiceRepository.findById(paymentDto.getInvoiceId());
        if (byI.isEmpty()) return new ApiResponse("Failed", false);
        payment.setInvoice(byI.get());

        paymentRepository.save(payment);
        return new ApiResponse("Success", true, payment.getId());

    }

    public ApiResponse delete(Long id) {
        boolean b = paymentRepository.existsById(id);
        if (b) {
            paymentRepository.deleteById(id);
            return new ApiResponse("Success", true);
        } else {
            return new ApiResponse("Failed", false);
        }
    }
}
