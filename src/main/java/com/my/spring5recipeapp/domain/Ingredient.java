package com.my.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

//@Data
//changing @Data to @Getter and @Setter to avoid an Error related to lombok
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
// added to avoid getting into endless loop because of bi-drectional references and relations (lombok)
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
