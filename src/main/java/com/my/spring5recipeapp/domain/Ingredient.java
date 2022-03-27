package com.my.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne(cascade = CascadeType.ALL)
    //mappedBy="recipe"
    private Recipe recipe;
    //Every Ingredient has a UnitOfMeasure but UnitOfMeasure is expected-
    // to be a reference table and when we delete a row of Ingredients we dont want one UnitOfMeasure to be deleted
    //because ingredient only use table of UnitOfMeasure as a reference so their relationship has not any Cascade
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient(BigDecimal amount, String description, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    public Ingredient(BigDecimal amount, String description, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient() {

    }

}
