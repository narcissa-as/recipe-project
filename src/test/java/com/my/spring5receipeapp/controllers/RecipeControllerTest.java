package com.my.spring5receipeapp.controllers;

import com.my.spring5receipeapp.domain.Recipe;
import com.my.spring5receipeapp.service.RecipeServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/*
public class RecipeControllerTest {

RecipeController controller;

@Mock
        RecipeServiceImpl recipeService;
@Mock
Model model;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        controller=new RecipeController(recipeService);
    }

    @Test
    void getRecipePageTest() {
        /* firstly I made this type of Test,But I tested this stuff one time in RecipeServiceTestImpl and I mustnt repeat
        the test, here should just test the getPage method
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        when(recipeService.getRecipeById(1L)).thenReturn(recipe);*/
/*
        String result=controller.getMethodName(model);
        assertEquals(result,"recipe");

        verify(controller.(times(1)).getMethodName);
        MockMvc mockMvc=
    }
}*/