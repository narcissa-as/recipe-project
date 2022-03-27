package com.my.spring5recipeapp.converters;

import com.my.spring5recipeapp.commands.RecipeCommand;
import com.my.spring5recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
public static final Long RECIPE_ID=1L;
    public static final String DESCRIPTION="My Recipe";
    public static final String SOURCE="Source";
    public static final Integer COOK_TIME=Integer.valueOf("5");
    public static final Integer PREP_TIME=Integer.valueOf("7");
    public static final String DIRECTIONS="Directions";
    public static final Difficulty DIFFICULTY=Difficulty.Easy;
    public static final Integer Servings=Integer.valueOf("3");
    public static final String URL="Some URL";
    public static final Long CAT_ID_1=1L;
    public static final Long CAT_ID_2=2L;
    public static final Long INGRED_ID_1=3L;
    public static final Long INGRED_ID_2=4L;
    public static final Long NOTES_ID=9L;


    RecipeToRecipeCommand converter;
    @Before
    public void setUp() throws Exception {
        converter=new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand())
        ,new CategoryToCategoryCommand(),new NotesToNotesCommand());
    }
@Test
public void testNullObject() throws Exception{
        assertNull(converter.convert(null));
}

@Test
public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new Recipe()));
}
    @Test
    public void convert() throws Exception{
        //given
        Recipe recipe=new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setServing(Servings);
        recipe.setUrl(URL);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(Difficulty.Easy);
        recipe.setSource(SOURCE);

        Notes notes=new Notes();
        notes.setId(NOTES_ID);
        recipe.setNote(notes);

        Ingredient ingredient1=new Ingredient();
        ingredient1.setId(INGRED_ID_1);

        Ingredient ingredient2=new Ingredient();
        ingredient2.setId(INGRED_ID_2);
/*
        Set <Ingredient> ingredients=new HashSet<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        recipe.setIngredients(ingredients);*/
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        Category category1 =new Category();
        category1.setId(CAT_ID_1);

        Category category2=new Category();
        category2.setId(CAT_ID_2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        //when
        RecipeCommand command=converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(command.getId(),RECIPE_ID);
        assertEquals(command.getUrl(),URL);
        assertEquals(command.getCookTime(),COOK_TIME);
        assertEquals(command.getDescription(),DESCRIPTION);
        assertEquals(command.getDirections(),DIRECTIONS);
        assertEquals(command.getPrepTime(),PREP_TIME);
        assertEquals(command.getServing(),Servings);
        assertEquals(command.getSource(),SOURCE);
        assertEquals(command.getDifficulty(),DIFFICULTY);
        assertEquals(command.getNotes().getId(),NOTES_ID);
        assertEquals(command.getIngredients().size(),2);
        assertEquals(command.getCategories().size(),2);

    }
}