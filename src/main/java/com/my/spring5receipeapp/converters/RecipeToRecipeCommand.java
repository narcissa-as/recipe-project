package com.my.spring5receipeapp.converters;

import com.my.spring5receipeapp.commands.CategoryCommand;
import com.my.spring5receipeapp.commands.IngredientCommand;
import com.my.spring5receipeapp.commands.NotesCommand;
import com.my.spring5receipeapp.commands.RecipeCommand;
import com.my.spring5receipeapp.domain.Category;
import com.my.spring5receipeapp.domain.Ingredient;
import com.my.spring5receipeapp.domain.Notes;
import com.my.spring5receipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final IngredientToIngredientCommand ingredientConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
                                 CategoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        final RecipeCommand command = new RecipeCommand();
        if (source == null) {
            return null;
        }
        if (source != null) {
            command.setId(source.getId());
            command.setDescription(source.getDescription());
            command.setSource(source.getSource());
            command.setUrl(source.getUrl());
            command.setDirections(source.getDirections());
            command.setPrepTime(source.getPrepTime());
            command.setCookTime(source.getCookTime());
            command.setServing(source.getServing());
            command.setDifficulty(source.getDifficulty());
            command.setNotes(notesConverter.convert(source.getNote()));
            if (source.getIngredients() != null && source.getIngredients().size() > 0) {
                source.getIngredients().forEach(ingredient ->
                        command.getIngredients().add(ingredientConverter.convert(ingredient)));
            }
            if (source.getCategories() != null && source.getCategories().size() > 0) {
                source.getCategories()
                        .forEach(category -> command.getCategories().add(categoryConverter.convert(category)));
            }
        }
        return command;
    }
}
