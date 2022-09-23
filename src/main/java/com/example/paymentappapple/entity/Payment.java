package com.example.paymentappapple.entity;

import com.example.paymentappapple.entity.teplate.AbstractEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment extends AbstractEntity {

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp payment_time;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    private Invoice invoice;


}
