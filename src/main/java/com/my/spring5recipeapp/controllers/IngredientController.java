package com.my.spring5recipeapp.controllers;

import com.my.spring5recipeapp.commands.IngredientCommand;
import com.my.spring5recipeapp.commands.RecipeCommand;
import com.my.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.my.spring5recipeapp.domain.Ingredient;
import com.my.spring5recipeapp.domain.Recipe;
import com.my.spring5recipeapp.domain.UnitOfMeasure;
import com.my.spring5recipeapp.repository.RecipeRepository;
import com.my.spring5recipeapp.service.IngredientService;
import com.my.spring5recipeapp.service.RecipeService;
import com.my.spring5recipeapp.service.UnitOfMeasureService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, UnitOfMeasureService unitOfMeasureService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredient(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    String showRecipeIngredient(@PathVariable String recipeId,
                                @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        return "recipe/ingredient/show";

    }

    //create new ingredient
    //in this method we create an ingredient obj and set RecipeId that we have and know here and then send new
    // ingredient obj with the ingredient form to user to fill 3 other properties,ingredient Id value has been set
    //for it from setter in POJO
    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId, Model model) {
        // first we need to get the id of the recipe we want to add ingredient to
        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null
        //since the new ingredient is a detail of recipe, so we need to set the recipe id to this ingredient as
        // Foreign Key need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    //Method for showing the UPD page
    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,@PathVariable String id) {
       ingredientService.deleteById(Long.valueOf(recipeId),Long.valueOf(id));
       log.debug("Recipe id :" + recipeId + "ingredient id :" + "deleted");
        return "redirect:/recipe/"+recipeId+ "/ingredients";

    }

    // we have changed the ingredient values, and now it's turn to get the Ingredient Command object from the
    // form and save that back (persist it)
    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@PathVariable String recipeId, @ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        log.debug("saved recipe id=" + savedCommand.getRecipeId());
        log.debug("saved ingredient id=" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";

    }


}
