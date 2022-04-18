package com.my.spring5recipeapp.controllers;

import com.my.spring5recipeapp.commands.RecipeCommand;
import com.my.spring5recipeapp.domain.Recipe;
import com.my.spring5recipeapp.exceptions.NotFoundException;
import com.my.spring5recipeapp.service.RecipeService;
import org.junit.Before;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController controller;
    MockMvc mockMvc;

    /*
        @Before
        public void setUp() throws Exception {
            MockitoAnnotations.openMocks(this);
            controller=new RecipeController(recipeService);
        }*/

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getRecipePageTest() throws Exception {
        /* firstly I made this type of Test,But I tested this stuff one time in
        RecipeServiceTestImpl and I found that I mustn't repeat
        the test, here should just test the getPage method*/
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);


        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));

/*mine tests
        String result=controller.getMethodName(model);
        assertEquals(result,"recipe");

        verify(controller.(times(1)).getMethodName);
*/

    }

    @Test
    public void testGetRecipeNotFound() throws Exception {
        // I checked and creating new Recipe obj and setting the Id to 1 is not important unless it has a specific
        //reason that I don't know for now
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        //trainer has written this line too,but because it has never been  used I commented the line
        // RecipeCommand command=new RecipeCommand();
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);
        //here we are mimicking a post action with a recipe and test this process
        when(recipeService.saveRecipeCommand(any())).thenReturn(command);
        mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)// this line mimics the form post
                        .param("id", "")
                        .param("description", "some test string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {

        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);
        mockMvc.perform(get("/recipe/1/update")).andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    public void testDeleteAction() throws Exception {

        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(recipeService, times(1)).deleteById(anyLong());
    }

}