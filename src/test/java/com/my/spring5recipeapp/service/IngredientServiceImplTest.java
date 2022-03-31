package com.my.spring5recipeapp.service;

import com.my.spring5recipeapp.commands.IngredientCommand;
import com.my.spring5recipeapp.converters.IngredientCommandToIngredient;
import com.my.spring5recipeapp.converters.IngredientToIngredientCommand;
import com.my.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.my.spring5recipeapp.domain.Ingredient;
import com.my.spring5recipeapp.domain.Recipe;
import com.my.spring5recipeapp.repository.RecipeRepository;
import com.my.spring5recipeapp.repository.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    // we do not need to mock converters
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest(IngredientCommandToIngredient ingredientCommandToIngredient, IngredientService ingredientService) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientService = ingredientService;
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand,
                ingredientCommandToIngredient, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndId() throws Exception {
    }

    @Test
    public void findByRecipeIdAndRecipeIdHappyPath() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient=new Ingredient();
        ingredient.setId(3L);

        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);

        Optional<Recipe> recipeOptional = Optional.of(recipe);
        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        //when(recipeRepository.save(recipe)).thenReturn(recipe);

        ingredientService.deleteById(1L,3L);

        verify(recipeRepository,times(1)).save(any(Recipe.class));
        verify(recipeRepository,times(1)).findById(anyLong());
    }
}