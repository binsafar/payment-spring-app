package com.example.paymentappapple.entity;

import com.example.paymentappapple.entity.teplate.AbstractEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 3)
    private String country;

    private String address;

    @Column(length = 50)
    private String phone;

;

}
