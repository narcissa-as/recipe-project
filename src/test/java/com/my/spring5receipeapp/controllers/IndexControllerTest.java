package com.my.spring5receipeapp.controllers;


import com.my.spring5receipeapp.domain.Recipe;
import com.my.spring5receipeapp.service.RecipeService;
//import jdk.internal.org.jline.utils.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.Timeout;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.View;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IndexControllerTest {
    IndexController controller;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        //Make a controller with needed Bean of injected Service in it
        MockitoAnnotations.openMocks(this);
        controller = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        //The controller that we made ready, get a model and return an "index"
        String check = controller.getIndexPage(model);
        assertEquals("index", check);
        //check times of invoke I guess
        verify(recipeService, times(1)).getRecipes();
        //check for the addAttribute to be "recipes" and be a Set
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }

    //if in method we have "throws Exception" we could have perform in method
    @Test
    public void mockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }


}