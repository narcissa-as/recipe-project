package com.my.spring5recipeapp.controllers;

import com.my.spring5recipeapp.commands.RecipeCommand;
import com.my.spring5recipeapp.service.ImageService;
import com.my.spring5recipeapp.service.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    //getting image from user and coming back to image attach form-this is the first part of getting image and before
    //hitting the submit button
    @GetMapping("/recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/imageuploadform";
    }

    //The @RequestParam is used to read the HTML form data provided by a user and bind it to the request parameter.
    // The Model contains the request data and provides it to view page.
    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);
        return ("redirect:/recipe/" + id + "/show");
    }
    //invoking this address is from show.HTML file
    //requesting a HttpServletResponse from Spring MVC in order to response this request(showing image)
    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        if (recipeCommand.getImage() != null) {
            byte[] byteArrays = new byte[recipeCommand.getImage().length];
            int i = 0;
            for (Byte wrappedByte : recipeCommand.getImage()) {
                byteArrays[i++] = wrappedByte;//auto unboxing
            }
            //setting the response
            response.setContentType("image/jpeg");
            InputStream is=new ByteArrayInputStream(byteArrays);
            IOUtils.copy(is,response.getOutputStream());
        }

    }
}
