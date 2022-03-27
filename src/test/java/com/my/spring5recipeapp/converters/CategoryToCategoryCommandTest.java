package com.my.spring5recipeapp.converters;

import com.my.spring5recipeapp.commands.CategoryCommand;
import com.my.spring5recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    public static final Long ID_VALUE=1L;
    public static final String DESCRIPTION="description";

CategoryToCategoryCommand converter;
    @Before
    public void setUp() throws Exception {
        converter=new CategoryToCategoryCommand();
    }
@Test
public void testNullParameter()throws Exception{
    assertNull(converter.convert(null));
}
    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new Category()));

    }
    @Test
    public void convert() throws Exception{
        //given
        Category category=new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand command=converter.convert(category);
        //then
        assertNotNull(command);
        assertEquals(command.getId(),ID_VALUE);
        assertEquals(command.getDescription(),DESCRIPTION);

    }
}