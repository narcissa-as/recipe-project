package com.my.spring5recipeapp.service;

import com.my.spring5recipeapp.commands.RecipeCommand;
import com.my.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.my.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.my.spring5recipeapp.domain.Recipe;
import com.my.spring5recipeapp.exceptions.NotFoundException;
import com.my.spring5recipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeRepository recipeRepository) {
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("This is Recipe log");
        Set<Recipe> recipesSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipesSet::add);
        return recipesSet;
        //Guess should be comment
        // Set <Recipe> recipeList=new HashSet<>();
        //recipeRepository.findAll().forEach();
    }

    public Recipe findById(Long along) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(along);
        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found, for ID value: "+
                    along);
        }
        //else
        return recipeOptional.get();

    }

    @Transactional
    @Override
    public RecipeCommand findCommandById(Long along) {

        return recipeToRecipeCommand.convert(findById(along));
    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        //not a Hibernate object,just POJO object
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("saved Recipe Id: " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);

    }

    @Override
    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }

  }
