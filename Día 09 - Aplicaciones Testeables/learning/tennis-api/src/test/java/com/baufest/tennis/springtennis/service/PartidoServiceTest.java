package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.mapper.JugadorMapper;
import com.baufest.tennis.springtennis.mapper.PartidoMapper;
import com.baufest.tennis.springtennis.mapper.PartidoMapperImpl;
import com.baufest.tennis.springtennis.model.Jugador;
import com.baufest.tennis.springtennis.model.Partido;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static com.baufest.tennis.springtennis.service.PartidoServiceImpl.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartidoServiceTest {

//    private PartidoMapper partidoMapper = Mappers.getMapper(PartidoMapper.class);

//    @Mock
//    PartidoMapper partidoMapper;

    private final List<Jugador> testJugadores = new ArrayList<>();
    private final List<Partido> testPartidos = new ArrayList<>();
    private List<PartidoDTO> testPartidosDTO = new ArrayList<>();

    private final Partido partidoToSave = new Partido();
    private PartidoDTO partidoDTOToSave = new PartidoDTO();


    private PartidoServiceImpl partidoService;
    PartidoMapper partidoMapper = new PartidoMapperImpl(JugadorMapper.INSTANCE);

    @Mock
    PartidoRepository partidoRepository;

    @BeforeEach
    void setUp() {
        setTestJugadores();
        setTestPartidos();
        setTestPartidosDTO();
        setPartidoAndPartidoDTOToSave();
        partidoService = new PartidoServiceImpl(partidoRepository, partidoMapper);
    }

    private void setPartidoAndPartidoDTOToSave() {
        partidoToSave.setJugadorLocal(testJugadores.get(1));
        partidoToSave.setJugadorVisitante(testJugadores.get(2));
        partidoToSave.setPuntosGameActualLocal("0");
        partidoToSave.setPuntosGameActualVisitante("0");
        partidoToSave.setCantidadGamesLocal(0);
        partidoToSave.setCantidadGamesVisitante(0);
        partidoToSave.setScoreVisitante(0);
        partidoToSave.setScoreLocal(0);
        partidoToSave.setEstado(Estado.NO_INICIADO);
        // Deprecated but it's Date xd
        partidoToSave.setFechaComienzo(new Date(2021, Calendar.NOVEMBER, 3));
        partidoDTOToSave = partidoMapper.toDTO(partidoToSave);
    }

    private void setTestJugadores() {
        testJugadores.clear();
        for (int i = 0; i < 3; i++) {
            testJugadores.add(new Jugador());
            testJugadores.get(i).setNombre("JUGADOR " + i);
            testJugadores.get(i).setId((long) (i + 1));
            testJugadores.get(i).setPuntos((int) (Math.random() * 50));
        }
    }

    private void setTestPartidos() {
        testPartidos.clear();
        for (int i = 0; i < 2; i++) {
            testPartidos.add(new Partido());
            testPartidos.get(i).setId((long) (i + 1));
            testPartidos.get(i).setEstado(Estado.NO_INICIADO);
            testPartidos.get(i).setJugadorVisitante(testJugadores.get(0));
            testPartidos.get(i).setJugadorLocal(testJugadores.get(1));
            testPartidos.get(i).setCantidadGamesLocal((int) (Math.random() * 6));
            testPartidos.get(i).setCantidadGamesVisitante((int) (Math.random() * 6));
            testPartidos.get(i).setFechaComienzo(new Date());
            testPartidos.get(i).setPuntosGameActualLocal("0");
            testPartidos.get(i).setPuntosGameActualVisitante("0");
            testPartidos.get(i).setScoreLocal(0);
            testPartidos.get(i).setScoreVisitante(0);
        }
        testPartidos.get(0).setCantidadGamesLocal(5);
        testPartidos.get(0).setPuntosGameActualLocal("40");
    }

    private void setTestPartidosDTO() {
        testPartidosDTO.clear();
        testPartidosDTO = testPartidos.stream().map(partidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Test
    void testListPartidosOK() {
        when(partidoRepository.findAll()).thenReturn(testPartidos);
        List<PartidoDTO> partidosDTO = partidoService.listAll();
        assertEquals(testPartidosDTO.size(), partidosDTO.size());
    }

    @Test
    void testGetByIdOK() {
        when(partidoRepository.findById(1L)).thenReturn(Optional.of(testPartidos.get(0)));
        PartidoDTO partidoDTO = partidoService.getById(1L);
        assertEquals(testPartidosDTO.get(0).getId(), partidoDTO.getId());
    }

    @Test
    void testSaveOK() {
        partidoService.save(partidoDTOToSave);

//        verify(partidoRepository, times(1)).save(partidoMapper.fromDTO(partidoDTOToSave));
        ArgumentCaptor<Partido> argumentCaptor = ArgumentCaptor.forClass(Partido.class);
        verify(partidoRepository, times(1)).save(argumentCaptor.capture());

        assertEquals(partidoToSave.getJugadorLocal().getId(),
                argumentCaptor.getValue().getJugadorLocal().getId());
    }

    @Test
    void testSaveByDateThrowsERROR() {
        // Erroneous date set
        partidoDTOToSave.setFechaComienzo(new Date(System.currentTimeMillis() - 100 * 1000));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            partidoService.save(partidoDTOToSave);
        });

        assertTrue(exception.getMessage().contains(INVALID_DATE));
    }

    @Test
    void testSaveByPlayersThrowsDuplicatedERROR() {
        // Same player set
        partidoToSave.setJugadorLocal(testJugadores.get(0));
        partidoToSave.setJugadorVisitante(testJugadores.get(0));
        partidoDTOToSave = partidoMapper.toDTO(partidoToSave);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            partidoService.save(partidoDTOToSave);
        });

        assertTrue(exception.getMessage().contains(PLAYER_DUPLICATED));
    }

    @Test
    void testSaveByPlayersThrowsMissingERROR() {
        // One player left
        partidoToSave.setJugadorLocal(null);
        partidoDTOToSave = partidoMapper.toDTO(partidoToSave);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            partidoService.save(partidoDTOToSave);
        });

        assertTrue(exception.getMessage().contains(PLAYER_MISSING));
    }

    @Test
    void testInitGameOK() {
        when(partidoRepository.findById(1L)).thenReturn(Optional.of(testPartidos.get(0)));
        partidoService.initGame(1L);

        ArgumentCaptor<Partido> saveArgumentCaptor = ArgumentCaptor.forClass(Partido.class);
        verify(partidoRepository, times(1)).save(saveArgumentCaptor.capture());

        ArgumentCaptor<Long> findByIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(partidoRepository, times(2)).findById(findByIdArgumentCaptor.capture());

        assertEquals(Estado.EN_CURSO, saveArgumentCaptor.getValue().getEstado());
    }

    @Test
    void testInitGameNotFoundERROR() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            partidoService.initGame(1L);
        });

        assertTrue(exception.getMessage().contains(PARTIDO_WITH_ID + 1L + DOES_NOT_EXIST));
    }

}
