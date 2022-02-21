package com.my.spring5receipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String source;
    private String url;
    @Lob
    private String directions;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    //more volume than 255 char in String
    @Lob
    private Byte[] image;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes note;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    public void setNote(Notes note) {
        this.note = note;
      //  note.setRecipe(this);
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }


}
