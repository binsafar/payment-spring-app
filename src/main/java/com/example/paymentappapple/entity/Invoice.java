package com.example.paymentappapple.entity;

import com.example.paymentappapple.entity.teplate.AbstractEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Invoice extends AbstractEntity {

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private Date issued;

    @Column(nullable = false)
    private Date due;

    @OneToOne
    private Order order;


}
