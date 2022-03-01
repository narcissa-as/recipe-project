package com.my.spring5receipeapp.converters;

import com.my.spring5receipeapp.commands.IngredientCommand;
import com.my.spring5receipeapp.commands.UnitOfMeasureCommand;
import com.my.spring5receipeapp.domain.Ingredient;
import com.my.spring5receipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
public static final Recipe RECIPE=new Recipe();
public static final BigDecimal AMOUNT=new BigDecimal("1");
public static final String DESCRIPTION  ="Chesseburger";
public static final Long ID_VALUE=1L;
public static final Long UOM_ID=2L;

    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter=new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }
@Test
public void TestNullObject(){
assertNull(converter.convert(null));
}
@Test
public void testEmptyObject(){
        assertNotNull(converter.convert(new IngredientCommand()));
}
    @Test
    public void convert() throws Exception {
IngredientCommand command=new IngredientCommand();
        //given
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand=new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUom(unitOfMeasureCommand);

        //when
        Ingredient ingredient=converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertEquals(ingredient.getId(),ID_VALUE);
        assertEquals(ingredient.getDescription(),DESCRIPTION);
        assertEquals(ingredient.getAmount(),AMOUNT);
        assertEquals(ingredient.getUom().getId(),UOM_ID);
    }
    @Test
    public void convertWithNullUOM()throws Exception{
        IngredientCommand command=new IngredientCommand();
        //given
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        command.setUom(null);

        //when
        Ingredient ingredient=converter.convert(command);
        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ingredient.getId(),ID_VALUE);
        assertEquals(ingredient.getDescription(),DESCRIPTION);
        assertEquals(ingredient.getAmount(),AMOUNT);

    }
}