package com.my.spring5recipeapp.service;


import com.my.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.my.spring5recipeapp.domain.UnitOfMeasure;
import com.my.spring5recipeapp.repository.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService unitOfMeasureService;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        unitOfMeasureService=new UnitOfMeasureServiceImpl(unitOfMeasureRepository,unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() {
        Set<UnitOfMeasure>uomSet=new HashSet<>();
        UnitOfMeasure uom1=new UnitOfMeasure();
        uom1.setId(1L);
        uomSet.add(uom1);

        UnitOfMeasure uom2=new UnitOfMeasure();
        uom2.setId(2L);
        uomSet.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(uomSet);
        //then
        assertEquals(uomSet.size(),2);
        verify(unitOfMeasureRepository,times(1));
    }
}