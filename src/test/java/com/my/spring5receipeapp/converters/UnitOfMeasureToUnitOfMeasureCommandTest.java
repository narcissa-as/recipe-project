package com.my.spring5receipeapp.converters;

import com.my.spring5receipeapp.commands.UnitOfMeasureCommand;
import com.my.spring5receipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {
    public static final String DESCRIPTION="description";
    public static final Long LONG_VALUE= 1L;
    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
    converter=new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObjectConvert() throws Exception{
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObj()throws Exception{
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }
    @Test
    public void convert() throws Exception{
        final UnitOfMeasure unitOfMeasure=new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        unitOfMeasure.setDescription("description");
        UnitOfMeasureCommand command =converter.convert(unitOfMeasure);
        assertNotNull(command);
        assertEquals(LONG_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());

    }
}