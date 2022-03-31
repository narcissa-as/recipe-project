package com.my.spring5recipeapp.controllers;

import com.my.spring5recipeapp.commands.RecipeCommand;
import com.my.spring5recipeapp.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //its a good habit to write @GetMapping/@PostMapping on methods to limit the methods
    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    //for creating a new Recipe obj
    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }
    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return ("recipe/recipeform");
    }

    //post command from a recipe object
    @PostMapping("recipe")
    //@ModelAttribute is an annotation from spring that matches the form post parameters to
    // the given object automatically
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        //redirect is a command that redirect to specific URL
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }
    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id, Model model) throws Exception {
        log.debug("Deleting id:" + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
