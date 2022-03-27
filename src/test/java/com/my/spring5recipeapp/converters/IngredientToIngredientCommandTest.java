package com.my.spring5recipeapp.converters;

import com.my.spring5recipeapp.commands.IngredientCommand;
import com.my.spring5recipeapp.domain.Ingredient;
import com.my.spring5recipeapp.domain.Recipe;
import com.my.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {
    public static final Recipe RECIPE=new Recipe();
    public static final Long ID_VALUE=1L;
    public static final String DESCRIPTION="cheeseburger";
    public static final Long UOM_ID=2L;
    public static final BigDecimal AMOUNT=new BigDecimal("1");


IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter=new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }
@Test
public void testNullConvert() throws Exception{
        assertNull(converter.convert(null));
}
@Test
public void testEmptyObject(){
assertNotNull(converter.convert(new Ingredient()));
}
    @Test
    public void convertNullUOM() throws Exception{
        Ingredient ingredient = new Ingredient();
        ingredient.setUom(null);
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        IngredientCommand command = converter.convert(ingredient);
        assertNull(command.getUom());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
    }
    @Test
        public void testConvertWithUom() throws Exception{
        Ingredient ingredient=new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure uom=new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);
        IngredientCommand ingredientCommand =converter.convert(ingredient);
        assertEquals(ID_VALUE,ingredientCommand.getId());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(UOM_ID,ingredientCommand.getUom().getId());
        assertEquals(AMOUNT,ingredientCommand.getAmount());

        assertNotNull(ingredientCommand.getUom());
    }
}