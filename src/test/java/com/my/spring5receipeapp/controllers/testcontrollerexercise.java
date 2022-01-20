package com.my.spring5receipeapp.controllers;

import com.my.spring5receipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testcontrollerexercise {
    IndexController indexController;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
       indexController=new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        String check= indexController.getIndexPage(model) ;
        assertEquals("index",check);
    }
}
