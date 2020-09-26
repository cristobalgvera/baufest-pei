package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.mapper.*;
import com.baufest.tennis.springtennis.model.Cancha;
import com.baufest.tennis.springtennis.repository.CanchaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.baufest.tennis.springtennis.service.PartidoServiceImpl.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CanchaServiceImplTest {

    @InjectMocks
    CanchaServiceImpl canchaServiceImpl;

    @Mock
    CanchaRepository canchaRepository;

    @Spy
    final CanchaMapper canchaMapper = new CanchaMapperImpl(new PartidoMapperImpl(JugadorMapper.INSTANCE));

//    CanchaMapper canchaMapper = new CanchaMapperImpl(PartidoMapper.INSTANCE);

    List<CanchaDTO> canchasDTODePrueba = new ArrayList<>();
    List<Cancha> canchasDePrueba = new ArrayList<>();

    JugadorDTO jugadorDTO1 = new JugadorDTO(1L, "Federer", SCORE_30);
    JugadorDTO jugadorDTO2 = new JugadorDTO(2L, "Nadal", SCORE_30);

    PartidoDTO partidoDTO = new PartidoDTO(1L, new Date(), Estado.NO_INICIADO, jugadorDTO1, jugadorDTO2, 0, 0, 0, 0);

    CanchaDTO canchaDTO = new CanchaDTO(1L, "CANCHA DE PRUEBA", "LAS PALOMAS 2569");

    PartidoDTO partidoDTOParaAgregar = new PartidoDTO(2L, new Date(), Estado.NO_INICIADO, jugadorDTO1, jugadorDTO2, 0, 0, 0, 0);
    CanchaDTO canchaDTOParaAgregar = new CanchaDTO("CANCHA PARA AGREGAR", "DIRECCIÓN PARA AGREGAR");
    Cancha canchaParaAgregar = new Cancha(1L, "CANCHA PARA AGREGAR", "DIRECCIÓN PARA AGREGAR");


    @BeforeEach
    void setUp() {
        canchaDTO.addPartido(partidoDTO);

        canchasDTODePrueba.clear();
        canchasDTODePrueba.add(canchaDTO);
        canchasDTODePrueba.add(new CanchaDTO());
        canchasDTODePrueba.add(new CanchaDTO());
        canchasDTODePrueba.add(new CanchaDTO());

        canchasDePrueba = canchasDTODePrueba.stream()
                .map(c -> canchaMapper.fromDTO(c, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());

//        canchaService = new CanchaService(canchaRepository, canchaMapper);
    }

    @Test
    void testSaveOK() {
        canchaServiceImpl.save(canchaDTOParaAgregar);
        ArgumentCaptor<Cancha> argumentCaptor = ArgumentCaptor.forClass(Cancha.class);
        verify(canchaRepository, times(1)).save(argumentCaptor.capture());

        assertEquals(canchaDTOParaAgregar.getNombre(), argumentCaptor.getValue().getNombre());
    }

    @Test
    void testFindAll() {
        when(canchaRepository.findAll()).thenReturn(canchasDePrueba);

        Collection<CanchaDTO> returnedCanchas = canchaServiceImpl.findAll();
        assertEquals(canchasDePrueba.size(), returnedCanchas.size());
    }

    /*
        @Test to set Partido in Cancha to prove hours error is
        in 'PartidoServiceTest.java', line 120. That's cos Partido
        needs Cancha, not the other way around.
    */

}
