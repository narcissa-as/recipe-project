package com.my.spring5recipeapp.commands;

import com.my.spring5recipeapp.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private String source;
    private String url;
    private String directions;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private Byte[] image;
    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> Categories = new HashSet<>();
    private NotesCommand notes;

}
