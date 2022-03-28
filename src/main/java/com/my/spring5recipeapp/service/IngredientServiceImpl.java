package com.my.spring5recipeapp.service;

import com.my.spring5recipeapp.commands.IngredientCommand;
import com.my.spring5recipeapp.converters.IngredientCommandToIngredient;
import com.my.spring5recipeapp.converters.IngredientToIngredientCommand;
import com.my.spring5recipeapp.domain.Ingredient;
import com.my.spring5recipeapp.domain.Recipe;
import com.my.spring5recipeapp.repository.RecipeRepository;
import com.my.spring5recipeapp.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }
    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            log.error("Recipe id not found." + recipeId);
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream().filter(ingredient ->
                        ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
        if (!ingredientCommandOptional.isPresent()) {
            log.error("Ingredient Id not found" + ingredientId);
        }
        return ingredientCommandOptional.get();
    }

    // to work with detached entity it's better to use transactional method
    @Transactional
    @Override

    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        // command (ingredient) is our detached entity, so first we have to get the Recipe related to the command(ingredient)
        // and then get the ingredient from it based on the command (ingredient command) id and then get the related UOM of it

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        //todo toss error if not found!
        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for id:" + command.getRecipeId());
            //?
            return new IngredientCommand();
        } else {

            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional =
                    recipe.getIngredients().
                            stream()
                            .filter(ingredient -> ingredient.getId().equals(command.getId()))
                            .findFirst();
            //update ingredient Entity of the Recipe
            // if there is an ingredient with the given id (an ingredient with an id were there from before, update it)
            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setId(command.getId());
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM Not Found")));
                //Save a new Ingredient for the Recipe
                //todo address this
            } else {
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }
            Recipe savedRecipe = recipeRepository.save(recipe);
            return
            ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId()
                            .equals(command.getId()))
                    .findFirst().get());
        }

    }
/* I guess s.th should be deleted
    Recipe updateRecipeIngredient(Recipe recipe) {
        Recipe recipe1 = new Recipe();
        recipe1 = recipe;
        recipe1.setId(recipe.getId());
        recipe1.setDescription(recipe.getDescription());
        recipe1.setDifficulty(recipe.getDifficulty());

        Recipe savedRecipe = recipeRepository.save(recipe1);
        return savedRecipe;
    }*/
}
