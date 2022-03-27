package com.my.spring5recipeapp.converters;

import com.my.spring5recipeapp.commands.NotesCommand;
import com.my.spring5recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
public static final Long ID_VALUE=1L;
public static final String RECIPE_NOTES="Notes";

NotesCommandToNotes converter;
    @Before
    public void setUp() throws Exception {
        converter=new NotesCommandToNotes();
    }
@Test
public void testNullParameter()throws Exception{
        converter.convert(null);
}
@Test
public void testEmptyObject()throws Exception{
       assertNotNull(converter.convert(new NotesCommand()));
}
    @Test
    public void convert()throws Exception {
        //given
        NotesCommand notesCommand=new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes=converter.convert(notesCommand);

        assertNotNull(notes);
        assertEquals(notes.getId(),ID_VALUE);
        assertEquals(notes.getRecipeNotes(),RECIPE_NOTES);

    }
}