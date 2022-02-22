package com.my.spring5receipeapp.converters;

import com.my.spring5receipeapp.commands.IngredientCommand;
import com.my.spring5receipeapp.commands.NotesCommand;
import com.my.spring5receipeapp.commands.RecipeCommand;
import com.my.spring5receipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter,
                                 CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setCookTime(source.getCookTime());
        recipe.setDirections(source.getDirections());
        recipe.setUrl(source.getUrl());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setUrl(source.getUrl());
        recipe.setSource(source.getSource());
        recipe.setServing(source.getServing());
        recipe.setNote(notesConverter.convert(source.getNotes()));
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }
        return recipe;
    }
}
