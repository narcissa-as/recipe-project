package com.my.spring5receipeapp.service;

import com.my.spring5receipeapp.commands.RecipeCommand;
import com.my.spring5receipeapp.domain.Ingredient;
import com.my.spring5receipeapp.domain.Recipe;

import java.util.Set;

@org.springframework.stereotype.Service
public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long along);
    RecipeCommand findCommandById(Long along);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(Long idToDelete);

}
