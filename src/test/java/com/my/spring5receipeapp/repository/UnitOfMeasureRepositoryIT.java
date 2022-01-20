package com.my.spring5receipeapp.repository;

import com.my.spring5receipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@DataJpaTest

public class UnitOfMeasureRepositoryIT {
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }
/*
    @Test
    public void findByDescription() {
      Optional <UnitOfMeasure> unitOfMeasureOptional= unitOfMeasureRepository.findByDescription("TeaSpoon");
      assertEquals("TeaSpoon",unitOfMeasureOptional.get().getDescription());
    }*/
}