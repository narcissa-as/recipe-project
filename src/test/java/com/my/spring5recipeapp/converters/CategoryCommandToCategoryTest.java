package com.my.spring5recipeapp.converters;

import com.my.spring5recipeapp.commands.CategoryCommand;
import com.my.spring5recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {
    public static final Long ID_VALUE=1L;
    public static final String Description="description";

CategoryCommandToCategory converter;
    @Before
    public void setUp() throws Exception {
        converter=new CategoryCommandToCategory();
    }
@Test
public void testNullParameter() throws Exception{
       assertNull(converter.convert(null));
}
@Test
public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new CategoryCommand()));
}

    @Test
    public void convert() throws Exception{
        //given
        CategoryCommand command=new CategoryCommand();
        command.setId(ID_VALUE);
        command.setDescription(Description);

        //when
        Category category=converter.convert(command);

        //then
        assertNotNull(category);
        assertEquals(category.getId(),ID_VALUE);
        assertEquals(category.getDescription(),Description);


    }
}