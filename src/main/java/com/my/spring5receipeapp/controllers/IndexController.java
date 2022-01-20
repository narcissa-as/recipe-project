package com.my.spring5receipeapp.controllers;

import com.my.spring5receipeapp.service.RecipeService;
//import lombok.extern.slf4j.Slf4j;
//import org.mockito.Mock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@Slf4j
@Controller
public class IndexController {

    RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        //log.debug("test controller");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";

    }
}
