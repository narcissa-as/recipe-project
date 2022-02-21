
package com.my.spring5receipeapp.bootstrap;

import com.my.spring5receipeapp.domain.*;
import com.my.spring5receipeapp.repository.CategoryRepository;
import com.my.spring5receipeapp.repository.RecipeRepository;
import com.my.spring5receipeapp.repository.UnitOfMeasureRepository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);
        //get UOMs
        //in every repository in app,have defined a findBy.. method
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM does not exist");
        }

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("TeaSpoon");
        if (!teaspoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> optionalTableSpoon = unitOfMeasureRepository.findByDescription("TableSpoon");
        if (!optionalTableSpoon.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        //get optionals for checking inserted values
        UnitOfMeasure eachUOM = eachUomOptional.get();
        UnitOfMeasure teaSpoonUOM = teaspoonOptional.get();
        UnitOfMeasure tableSpoonUOM = optionalTableSpoon.get();
        UnitOfMeasure dashUOM = dashUomOptional.get();
        UnitOfMeasure pintUOM = pintUomOptional.get();
        UnitOfMeasure cupUOM = cupUomOptional.get();

        Optional<Category> americanOptional = categoryRepository.findByDescription("American");
        if (!americanOptional.isPresent()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanOptional.isPresent()) {
            throw new RuntimeException("Expected category not found");
        }
        //get optionals for checking inserted values
        Category american = americanOptional.get();
        Category mexican = mexicanOptional.get();

        //Creat a Recipe object for guacamole
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Best Guacamole");
        guacamole.setDifficulty(Difficulty.Moderate);
        guacamole.setPrepTime(10);
        guacamole.setCookTime(10);
        guacamole.setSource("Internet");
        guacamole.setUrl("https://www.simplyrecipes.com/");
        guacamole.setDirections("1. Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon.Place in a bowl.2. Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Dont overdo it! The guacamole should be a little chunky.)3. Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.4. Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips. homemade tortilla chips. Refrigerate leftover guacamole up to 3 days.");
        Notes guacNotes = new Notes();
        //Added in Recipe setNotes in code review
        //guacNotes.setRecipe(guacamole);
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados." + "\n" + "add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great.");
        guacamole.setNote(guacNotes);
        //at first we have the first line for defining an ingredient for a Recipe,the reason for using get rather than set is
        //list populating initialization error avoiding, and then because we have related Recipe when we define an ingredient
        //we decide to set the Recipe value in a helper method named addIngredient with omitting the last parameter we use frequently for Recipe value.
        //guacamole.getIngredient().add(new Ingredient(new BigDecimal(2), "Ripe Avocados", eachUOM, guacamole));
        guacamole.addIngredient(new Ingredient(new BigDecimal(2), "Ripe Avocados", eachUOM));
        guacamole.addIngredient(new Ingredient(new BigDecimal(1.4), "salt, plus more to taste", teaSpoonUOM));
        guacamole.addIngredient(new Ingredient(new BigDecimal(1), "fresh lime or lemon juice", tableSpoonUOM));
        guacamole.addIngredient(new Ingredient(new BigDecimal(2), "minced red onion or thinly sliced green onion", tableSpoonUOM));
        guacamole.addIngredient(new Ingredient(new BigDecimal(1), "serrano (or jalapeño) chilis, stems and seeds removed, minced", eachUOM));
        guacamole.addIngredient(new Ingredient(new BigDecimal(2), "cilantro (leaves and tender stems), finely chopped", tableSpoonUOM));
        guacamole.addIngredient(new Ingredient(new BigDecimal(1), "Pinch freshly ground black pepper", pintUOM));
        guacamole.addIngredient(new Ingredient(new BigDecimal(1), "ripe tomato, chopped (optional)", eachUOM));

        guacamole.getCategories().add(american);
        guacamole.getCategories().add(mexican);

        recipes.add(guacamole);

        //Creat a Recipe object for "Spicy Grilled Chicken tacos"
        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken tacos");
        tacos.setPrepTime(20);
        tacos.setCookTime(10);
        tacos.setServing(2);
        tacos.setUrl("https://www.simplyrecipes.com/");
        tacos.setSource("Internet");
        tacos.setDifficulty(Difficulty.Moderate);


        Notes tacoNote = new Notes();
        tacoNote.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        //No more required because added in Recipe setNotes in code review
        //tacoNote.setRecipe(tacos);

        tacos.setNote(tacoNote);
        tacos.setDirections("Prepare a gas or charcoal grill for medium-high, direct heat\n" +
                "Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        //Using get.add to avoid getting null Error for initializing first instance
        tacos.getCategories().add(american);
        tacos.getCategories().add(mexican);
        //get required UOMs
        Optional<UnitOfMeasure> cloveOptional = unitOfMeasureRepository.findByDescription("Clove");
        if (!cloveOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        //pounds
        Optional<UnitOfMeasure> poundsOptional = unitOfMeasureRepository.findByDescription("Pounds");
        if (!poundsOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        //get optionals for checking inserted values
        UnitOfMeasure cloveUOM = cloveOptional.get();
        UnitOfMeasure poundsUOM = poundsOptional.get();

/*
        tacos.getIngredient().add(new Ingredient(new BigDecimal(2), "ancho chili powder", tableSpoonUOM, tacos));
        tacos.getIngredient().add(new Ingredient(new BigDecimal(1), "dried oregano", teaSpoonUOM, tacos));
        tacos.getIngredient().add(new Ingredient(new BigDecimal(1), " dried cumin", teaSpoonUOM, tacos));
        tacos.getIngredient().add(new Ingredient(new BigDecimal(1), "sugar", teaSpoonUOM, tacos));
        tacos.getIngredient().add(new Ingredient(new BigDecimal(1.2), "salt", teaSpoonUOM, tacos));
        tacos.getIngredient().add(new Ingredient(new BigDecimal(1), "garlic, finely chopped", cloveUOM, tacos));
        tacos.getIngredient().add(new Ingredient(new BigDecimal(1), "finely grated orange zest", tableSpoonUOM, tacos));
        tacos.getIngredient().add(new Ingredient(new BigDecimal(3), "fresh-squeezed orange juice", tableSpoonUOM, tacos));
        tacos.getIngredient().add(new Ingredient(new BigDecimal(2), "olive oil", tableSpoonUOM, tacos));
        tacos.getIngredient().add(new Ingredient(new BigDecimal(4), "skinless, boneless chicken thighs (1 1/4 pounds)", poundsUOM, tacos));*/

        tacos.addIngredient(new Ingredient(new BigDecimal(2), "ancho chili powder", tableSpoonUOM));
        tacos.addIngredient(new Ingredient(new BigDecimal(1), "dried oregano", teaSpoonUOM));
        tacos.addIngredient(new Ingredient(new BigDecimal(1), " dried cumin", teaSpoonUOM));
        tacos.addIngredient(new Ingredient(new BigDecimal(1), "sugar", teaSpoonUOM));
        tacos.addIngredient(new Ingredient(new BigDecimal(1.2), "salt", teaSpoonUOM));
        tacos.addIngredient(new Ingredient(new BigDecimal(1), "garlic, finely chopped", cloveUOM));
        tacos.addIngredient(new Ingredient(new BigDecimal(1), "finely grated orange zest", tableSpoonUOM));
        tacos.addIngredient(new Ingredient(new BigDecimal(3), "fresh-squeezed orange juice", tableSpoonUOM));
        tacos.addIngredient(new Ingredient(new BigDecimal(2), "olive oil", tableSpoonUOM));
        tacos.addIngredient(new Ingredient(new BigDecimal(4), "skinless, boneless chicken thighs (1 1/4 pounds)", poundsUOM));

        recipes.add(tacos);
        return recipes;

    }
}
