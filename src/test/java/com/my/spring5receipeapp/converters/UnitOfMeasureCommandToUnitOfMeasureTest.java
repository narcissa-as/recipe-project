package com.my.spring5receipeapp.converters;

import com.my.spring5receipeapp.commands.UnitOfMeasureCommand;
import com.my.spring5receipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
public static final String DESCRIPTION ="description";
public static final Long LONG_Value=1L;
    UnitOfMeasureCommandToUnitOfMeasure converter ;
    @Before
    public void setUp() throws Exception {
        converter=new UnitOfMeasureCommandToUnitOfMeasure();
    }
@Test
    public void testNullParameter()throws Exception{
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObj()throws Exception{
       assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        UnitOfMeasureCommand command=new UnitOfMeasureCommand();
        command.setId(LONG_Value);
        command.setDescription(DESCRIPTION);
       UnitOfMeasure uom = converter.convert(command);
        assertNotNull(uom);
        assertEquals(LONG_Value,uom.getId());
        assertEquals(DESCRIPTION,uom.getDescription());
    }
}