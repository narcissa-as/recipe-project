package com.my.spring5receipeapp.service;

import com.my.spring5receipeapp.converters.RecipeCommandToRecipe;
import com.my.spring5receipeapp.converters.RecipeToRecipeCommand;
import com.my.spring5receipeapp.domain.Recipe;
import com.my.spring5receipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
        //setup method is for initializing needed Beans
    void setUp() {
        //with this command, Mockito initialize and give the mock to us
        MockitoAnnotations.openMocks(this);
        //here we  inject the needed been to our main class object, then we have the ready Bean for Test.

        recipeService = new RecipeServiceImpl(recipeToRecipeCommand, recipeCommandToRecipe, recipeRepository);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);
        //In Mockito, you can specify what to return when a method is called
        //It should be used when we want to mock to return specific values when particular methods are called. In simple
        // terms, "When the XYZ() method is called, then return ABC." It is mostly used when there is some condition to
        // execute.
        //"thenreturn" method lets you define the return value when a particular method of the mocked object
        // is been called.
        when(recipeRepository.findAll()).thenReturn(recipeData);

        /*Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 0);
        verify(recipeRepository,times(0)).findAll();*/


    }

    @Test
    void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(any())).thenReturn(recipeOptional);
        Recipe recipeReturned = recipeService.findById(1L);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
        assertEquals(recipeReturned, recipe);
        assertNotNull(recipeReturned);

    }

}