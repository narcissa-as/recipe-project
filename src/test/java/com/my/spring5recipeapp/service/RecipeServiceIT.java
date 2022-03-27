package com.my.spring5recipeapp.service;

import com.my.spring5recipeapp.commands.RecipeCommand;
import com.my.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.my.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.my.spring5recipeapp.domain.Recipe;
import com.my.spring5recipeapp.repository.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

//in this class we test in this way: we get Data using Repository, change it and save it in DB using our Service
// then check it out to have the expected result
@RunWith(SpringRunner.class)
@SpringBootTest
//here we have 2 options for ITtests,if we use @DataJPA since we have only a slice of SpringContext(Embedded DB
//and SpringContext config) we couldnt have other beans except Repository and such Beans,so our Test fails.
//so we need to Use @SpringBootTest ann here because of injection of different Beans
public class RecipeServiceIT {
    public static final String DESCRIPTION = "Description";
    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Transactional
    @Test
    public void testSaveDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        testRecipe.setDescription(DESCRIPTION);
        //when
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);


        //then
        assertNotNull(savedRecipeCommand);
        assertEquals(savedRecipeCommand.getDescription(), DESCRIPTION);
        assertEquals(testRecipeCommand.getId(), savedRecipeCommand.getId());
        assertEquals(savedRecipeCommand.getCategories().size(),testRecipeCommand.getCategories().size());
        assertEquals(savedRecipeCommand.getIngredients().size(),testRecipeCommand.getIngredients().size());
    }


}
