package com.my.spring5receipeapp.controllers;

import com.my.spring5receipeapp.domain.Recipe;
import com.my.spring5receipeapp.service.RecipeService;
import org.junit.Before;
import org.junit.Ignore;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

@Mock
RecipeService recipeService;

    RecipeController controller;
/*
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        controller=new RecipeController(recipeService);
    }*/
@Before
public void setUp()throws Exception{
MockitoAnnotations.openMocks(this);
controller= new RecipeController(recipeService);
}
    @Test
    public void getRecipePageTest() throws Exception{
        /* firstly I made this type of Test,But I tested this stuff one time in RecipeServiceTestImpl and I mustn't repeat
        the test, here should just test the getPage method*/
        Recipe recipe=new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
        when(recipeService.findById(anyLong())).thenReturn(recipe);


        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));

/*mine tests
        String result=controller.getMethodName(model);
        assertEquals(result,"recipe");

        verify(controller.(times(1)).getMethodName);
*/
    }

}