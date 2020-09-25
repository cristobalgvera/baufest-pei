package com.baufest.tennis.springtennis.mapper;

import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.model.Cancha;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PartidoMapper.class}, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CanchaMapper {
    CanchaMapper INSTANCE = Mappers.getMapper(CanchaMapper.class);

    CanchaDTO toDTO(Cancha cancha, @Context CycleAvoidingMappingContext context);
    Cancha fromDTO(CanchaDTO canchaDTO, @Context CycleAvoidingMappingContext context);
}
