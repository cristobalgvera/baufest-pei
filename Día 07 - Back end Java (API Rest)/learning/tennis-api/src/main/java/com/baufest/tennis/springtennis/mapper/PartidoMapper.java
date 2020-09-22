package com.baufest.tennis.springtennis.mapper;

import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.model.Partido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartidoMapper {
    PartidoMapper INSTANCE = Mappers.getMapper(PartidoMapper.class);

    PartidoDTO toDTO(Partido partido);
    Partido fromDTO(PartidoDTO partidoDTO);
}
