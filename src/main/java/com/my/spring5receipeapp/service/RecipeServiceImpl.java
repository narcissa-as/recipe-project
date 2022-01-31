package com.my.spring5receipeapp.service;

import com.my.spring5receipeapp.domain.Recipe;
import com.my.spring5receipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@org.springframework.stereotype.Service
public class RecipeServiceImpl implements RecipeService {


    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
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
    public Recipe findById(Long along){
       Optional <Recipe> recipeOptional=recipeRepository.findById(along);
       if (!recipeOptional.isPresent()) {
           throw new RuntimeException("Recipe Not Found");
       }
              //else
           return recipeOptional.get();

    }
}
