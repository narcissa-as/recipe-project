package com.my.spring5receipeapp.converters;

import com.my.spring5receipeapp.commands.NotesCommand;
import com.my.spring5receipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    public static final Long ID_VALUE=1L;
    public static final String RECIPE_NOTES ="Notes";
NotesToNotesCommand converter;
    @Before
    public void setUp() throws Exception {
        converter=new NotesToNotesCommand();
    }
    @Test
    public void convert() throws Exception{
        //given
        Notes notes=new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand command=converter.convert(notes);

        //then
        assertNotNull(command);
        assertEquals(command.getId(),ID_VALUE);
        assertEquals(command.getRecipeNotes(),RECIPE_NOTES);

    }
@Test
public void testNull() throws Exception{
        assertNull(converter.convert(null));
}
@Test
public void testEmptyObject()throws Exception{
        assertNotNull(converter.convert(new Notes()));

}

}