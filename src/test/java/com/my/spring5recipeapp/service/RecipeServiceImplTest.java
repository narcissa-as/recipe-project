package com.my.spring5recipeapp.service;

import com.my.spring5recipeapp.commands.RecipeCommand;
import com.my.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.my.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.my.spring5recipeapp.domain.Recipe;
import com.my.spring5recipeapp.exceptions.NotFoundException;
import com.my.spring5recipeapp.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    //setup method is for initializing needed Beans
    public void setUp() throws Exception {
        //with this command, Mockito initialize and give the mock to us
        MockitoAnnotations.openMocks(this);
        //here we  inject the needed been to our main class object, then we have the ready Bean for Test.

        recipeService = new RecipeServiceImpl(recipeToRecipeCommand, recipeCommandToRecipe, recipeRepository);
    }

    @Test
    public void getRecipes() {
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
    public void getRecipeByIdTest() throws Exception {
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

    @Test
    public void getRecipeCommandByIdTest() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand command = new RecipeCommand();
        command.setId(1L);
        when(recipeToRecipeCommand.convert(any())).thenReturn(command);

        RecipeCommand commandById = recipeService.findCommandById(1L);
        //then
        //my recommendation test
        //assertEquals(commandById.getId(), command.getId());
        //but these are tutor's test
        assertNotNull("Null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getDeleteByIdTest() throws Exception {
        //given
        Long idToDelete = Long.valueOf(2L);
        //when
        recipeService.deleteById(idToDelete);
        //then
        //no when,since method has void return type
        verify(recipeRepository, times(1)).deleteById(idToDelete);
    }
    @Test (expected= NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception{
        Optional <Recipe> recipeOptional=Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe returnedRecipe=recipeService.findById(1L);
        //should go boom
    }
}