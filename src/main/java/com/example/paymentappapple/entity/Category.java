package com.example.paymentappapple.entity;

import com.example.paymentappapple.entity.teplate.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity {

    @Column(length = 225)
    private String name;

}
