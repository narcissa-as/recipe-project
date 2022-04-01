package com.my.spring5recipeapp.controllers;

import com.my.spring5recipeapp.service.ImageService;
import com.my.spring5recipeapp.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/imageuploadform";
    }
//The @RequestParam is used to read the HTML form data provided by a user and bind it to the request parameter.
// The Model contains the request data and provides it to view page.
    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam ("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);
        return ("redirect:/recipe/" + id + "/show");
    }
}
