package com.my.spring5receipeapp.converters;

import com.my.spring5receipeapp.commands.IngredientCommand;
import com.my.spring5receipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }
@Synchronized
@Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUnitOfMeasure()));
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        return ingredient;
    }
}
