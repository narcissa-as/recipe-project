package com.my.spring5recipeapp.converters;

import com.my.spring5recipeapp.commands.CategoryCommand;
import com.my.spring5recipeapp.commands.IngredientCommand;
import com.my.spring5recipeapp.commands.NotesCommand;
import com.my.spring5recipeapp.commands.RecipeCommand;
import com.my.spring5recipeapp.domain.Difficulty;
import com.my.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    public static final Long RECIPE_ID = 1L;
    public static final String DESCRIPTION = "My Recipe";
    public static final String SOURCE = "Source";
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.Easy;
    public static final Integer Servings = Integer.valueOf("3");
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure())
                , new CategoryCommandToCategory(), new NotesCommandToNotes());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setDifficulty(Difficulty.Easy);
        command.setCookTime(COOK_TIME);
        command.setServing(Servings);
        command.setPrepTime(PREP_TIME);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDescription(DESCRIPTION);
        command.setSource(SOURCE);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        command.setNotes(notesCommand);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_1);
        command.getCategories().add(categoryCommand1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CAT_ID_2);
        command.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGRED_ID_1);
        command.getIngredients().add(ingredientCommand1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGRED_ID_2);
        command.getIngredients().add(ingredientCommand2);
//when
        Recipe recipe = converter.convert(command);

//then
        assertNotNull(recipe);
        assertEquals(recipe.getId(), RECIPE_ID);
        assertEquals(recipe.getDescription(), DESCRIPTION);
        assertEquals(recipe.getCookTime(), COOK_TIME);
        assertEquals(recipe.getDifficulty(),DIFFICULTY);
        assertEquals(recipe.getDirections(), DIRECTIONS);
        assertEquals(recipe.getPrepTime(), PREP_TIME);
        assertEquals(recipe.getServing(), Servings);
        assertEquals(recipe.getSource(), SOURCE);
        assertEquals(recipe.getUrl(), URL);
        assertEquals(recipe.getCategories().size(), 2);
        assertEquals(recipe.getNote().getId(), NOTES_ID);
        assertEquals(recipe.getIngredients().size(), 2);


    }
}