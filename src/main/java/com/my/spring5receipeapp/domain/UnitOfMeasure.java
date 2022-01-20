package com.my.spring5receipeapp.domain;

import lombok.Data;

import javax.persistence.*;
@Data
//the description of relationship with Ingredient can be found in comments of Ingredient's
@Entity
//@Table(name = "unit_of_measure")
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @OneToOne
    private Ingredient ingredient;
}
