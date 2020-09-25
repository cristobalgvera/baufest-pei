package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.mapper.CanchaMapper;
import com.baufest.tennis.springtennis.mapper.CanchaMapperImpl;
import com.baufest.tennis.springtennis.mapper.PartidoMapper;
import com.baufest.tennis.springtennis.model.Cancha;
import com.baufest.tennis.springtennis.model.Partido;
import com.baufest.tennis.springtennis.repository.CanchaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.baufest.tennis.springtennis.service.PartidoServiceImpl.*;

@ExtendWith(MockitoExtension.class)
public class CanchaServiceTest {

    @InjectMocks
    CanchaService canchaService;

    @Mock
    CanchaRepository canchaRepository;

    @Spy
    CanchaMapper canchaMapper = new CanchaMapperImpl(PartidoMapper.INSTANCE);

    List<CanchaDTO> canchasDTODePrueba = new ArrayList<>();
    List<Cancha> canchasDePrueba = new ArrayList<>();

    JugadorDTO jugadorDTO1 = new JugadorDTO(1L, "Federer", SCORE_30);
    JugadorDTO jugadorDTO2 = new JugadorDTO(2L, "Nadal", SCORE_30);

    PartidoDTO partidoDTO = new PartidoDTO(1L, new Date(), Estado.NO_INICIADO, jugadorDTO1, jugadorDTO2, 0, 0, 0, 0);

    Cancha cancha = new Cancha(1L, "CANCHA DE PRUEBA", "LAS PALOMAS 2569");

    CanchaDTO canchaDTO = new CanchaDTO(1L, "CANCHA DE PRUEBA", "LAS PALOMAS 2569");

    PartidoDTO partidoDTOParaAgregar = new PartidoDTO(2L, new Date(), Estado.NO_INICIADO, jugadorDTO1, jugadorDTO2, 0, 0, 0, 0);

    @BeforeEach
    void setUp() {
        canchaDTO.addPartido(partidoDTO);

        canchasDTODePrueba.clear();
        canchasDTODePrueba.add(canchaDTO);
    }


}
