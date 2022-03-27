package com.my.spring5recipeapp.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {
    //set a category as a property for testing aim
    Category category;
    //because we have 3 methods here, it prevent headaches to have a new object before any
    //test method runs
    @Before("")
    @BeforeEach
    public void setUp(){
        //initialize category property
    category=new Category();
    }
    @Test
    void getId() throws Exception {
       // category=new Category();
        Long idValue=4L;
        category.setId(idValue);
        assertEquals(idValue,category.getId()); 
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}