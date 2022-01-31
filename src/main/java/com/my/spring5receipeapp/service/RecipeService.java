package com.my.spring5receipeapp.service;

import com.my.spring5receipeapp.domain.Recipe;

import java.util.Set;

@org.springframework.stereotype.Service
public interface RecipeService {
    public Set<Recipe> getRecipes();
    public Recipe findById(Long along);
}
