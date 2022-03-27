package com.my.spring5recipeapp.repository;

import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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