package com.example.paymentappapple.entity;

import com.example.paymentappapple.entity.teplate.AbstractEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order extends AbstractEntity {

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    private Customer customer;



}
