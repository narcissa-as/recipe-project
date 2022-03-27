package com.my.spring5recipeapp.converters;

import com.my.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.my.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
   @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
       final UnitOfMeasure uom = new UnitOfMeasure();
       if (source == null) {
           return null;
       }
       uom.setId(source.getId());
       uom.setDescription(source.getDescription());
       return uom;


    }
}
