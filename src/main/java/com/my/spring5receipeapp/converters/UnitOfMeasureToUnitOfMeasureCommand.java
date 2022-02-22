package com.my.spring5receipeapp.converters;

import com.my.spring5receipeapp.commands.UnitOfMeasureCommand;
import com.my.spring5receipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter <UnitOfMeasure,UnitOfMeasureCommand> {
    // since spring does not guarantee thread safety, so we use Project Lombok Synchronized and thread-safe so we can run this in a multi-threaded environment
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        // declaring variables final to be immutable
        final UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
        if (unitOfMeasure != null) {
            uomc.setId(unitOfMeasure.getId());
            uomc.setDescription(unitOfMeasure.getDescription());
            return uomc;
        }
        return null;
    }
}
