package com.example.paymentappapple.entity;

import com.example.paymentappapple.entity.teplate.AbstractEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends AbstractEntity {

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String description;

    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attachment attachment;


    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;


}
