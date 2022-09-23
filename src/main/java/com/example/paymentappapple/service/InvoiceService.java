package com.example.paymentappapple.service;

import com.example.paymentappapple.dto.InvoiceDto;
import com.example.paymentappapple.entity.Invoice;
import com.example.paymentappapple.entity.Order;
import com.example.paymentappapple.repository.InvoiceRepository;
import com.example.paymentappapple.repository.OrderRepository;
import com.example.paymentappapple.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    final InvoiceRepository invoiceRepository;

    final OrderRepository orderRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, OrderRepository orderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
    }

    public ApiResponse add(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setDue(invoiceDto.getDue());
        invoice.setIssued(invoiceDto.getIssued());
        Optional<Order> byId = orderRepository.findById(invoiceDto.getOrderId());
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        invoice.setOrder(byId.get());
        invoiceRepository.save(invoice);
        return new ApiResponse("Success", true, invoice.getId());
    }

    public ApiResponse getOne(Long id) {
        Optional<Invoice> byId = invoiceRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        return new ApiResponse("Success", true, byId.get());
    }

    public ApiResponse getAll() {
        List<Invoice> all = invoiceRepository.findAll();
        if (all.isEmpty()) return new ApiResponse("Empty", true);
        return new ApiResponse("Success", true, all);
    }

    public ApiResponse update(Long id, InvoiceDto invoiceDto) {
        Optional<Invoice> byId = invoiceRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Failed", false);
        Invoice invoice = byId.get();
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setDue(invoiceDto.getDue());
        invoice.setIssued(invoiceDto.getIssued());
        Optional<Order> byI = orderRepository.findById(invoiceDto.getOrderId());
        if (byI.isEmpty()) return new ApiResponse("Failed", false);
        invoice.setOrder(byI.get());
        invoiceRepository.save(invoice);
        return new ApiResponse("Success", true, invoice.getId());
    }

    public ApiResponse delete(Long id) {
        boolean b = invoiceRepository.existsById(id);
        if (b){
            invoiceRepository.deleteById(id);
            return new ApiResponse("Success", true);
        }else {
            return new ApiResponse("Failed", false);
        }
    }
}
