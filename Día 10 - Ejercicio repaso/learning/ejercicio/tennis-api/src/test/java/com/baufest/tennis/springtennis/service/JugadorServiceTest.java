package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.mapper.CycleAvoidingMappingContext;
import com.baufest.tennis.springtennis.mapper.JugadorMapper;
import com.baufest.tennis.springtennis.model.Jugador;
import com.baufest.tennis.springtennis.model.Partido;
import com.baufest.tennis.springtennis.repository.JugadorRepository;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JugadorServiceTest {
    private final List<Jugador> jugadoresDePrueba = new ArrayList<>();
    private final List<JugadorDTO> jugadoresDTODePrueba = new ArrayList<>();
    private final List<PartidoDTO> partidosDTODePruebaLocal = new ArrayList<>();
    private final List<PartidoDTO> partidosDTODePruebaVisita = new ArrayList<>();
    private final JugadorDTO jugadorParaAgregar = new JugadorDTO();

    @InjectMocks
    JugadorServiceImpl jugadorService;

    @Mock
    JugadorRepository jugadorRepository;

    @Mock
    PartidoRepository partidoRepository;

    @Spy
    JugadorMapper jugadorMapper = JugadorMapper.INSTANCE;

    @BeforeEach
    public void setUp() {
        jugadoresDTODePrueba.clear();
        jugadoresDTODePrueba.add(new JugadorDTO());
        jugadoresDTODePrueba.add(new JugadorDTO());
        jugadoresDTODePrueba.add(new JugadorDTO());
        jugadoresDTODePrueba.add(new JugadorDTO());
        jugadoresDTODePrueba.get(0).setNombre("facu");
        jugadoresDTODePrueba.get(1).setNombre("fer");
        jugadoresDTODePrueba.get(2).setNombre("juli");
        jugadoresDTODePrueba.get(3).setNombre("axel");
        jugadoresDTODePrueba.get(0).setId(1L);
        jugadoresDTODePrueba.get(1).setId(2L);
        jugadoresDTODePrueba.get(2).setId(3L);
        jugadoresDTODePrueba.get(3).setId(4L);
        jugadoresDTODePrueba.get(0).setPuntos(20);
        jugadoresDTODePrueba.get(1).setPuntos(15);
        jugadoresDTODePrueba.get(2).setPuntos(10);
        jugadoresDTODePrueba.get(3).setPuntos(5);

        jugadoresDePrueba.clear();
        jugadoresDePrueba.add(new Jugador());
        jugadoresDePrueba.add(new Jugador());
        jugadoresDePrueba.add(new Jugador());
        jugadoresDePrueba.add(new Jugador());
        jugadoresDePrueba.get(0).setNombre("facu");
        jugadoresDePrueba.get(1).setNombre("fer");
        jugadoresDePrueba.get(2).setNombre("juli");
        jugadoresDePrueba.get(3).setNombre("axel");
        jugadoresDePrueba.get(0).setId(1L);
        jugadoresDePrueba.get(1).setId(2L);
        jugadoresDePrueba.get(2).setId(3L);
        jugadoresDePrueba.get(3).setId(4L);
        jugadoresDePrueba.get(0).setPuntos(20);
        jugadoresDePrueba.get(1).setPuntos(15);
        jugadoresDePrueba.get(2).setPuntos(10);
        jugadoresDePrueba.get(3).setPuntos(5);

        jugadorParaAgregar.setId(5L);
        jugadorParaAgregar.setNombre("lucas");
        jugadorParaAgregar.setPuntos(25);

        partidosDTODePruebaLocal.clear();
    }

    @Test
    void testListJugadores() {
        when(jugadorRepository.findAllByOrderByNombreAsc()).thenReturn(jugadoresDePrueba);
        List<JugadorDTO> jugadoresConseguidos = jugadorService.listAll();
        assertEquals(jugadoresDTODePrueba.size(), jugadoresConseguidos.size());
    }

    @Test
    void testGetJugadorByID() {
        when(jugadorRepository.findById(jugadoresDTODePrueba.get(0).getId())).thenReturn(Optional.of(jugadoresDePrueba.get(0)));
        JugadorDTO jugadorEncontrado = jugadorService.getById(jugadoresDTODePrueba.get(0).getId());
        assertEquals(jugadoresDTODePrueba.get(0).getId(), jugadorEncontrado.getId());
    }

    @Test
    void testSaveOrUpdate() {
        jugadorService.save(jugadorParaAgregar);

        ArgumentCaptor<Jugador> argumentCaptor = ArgumentCaptor.forClass(Jugador.class);
        verify(jugadorRepository).save(argumentCaptor.capture());
        assertEquals(jugadorParaAgregar.getId(), argumentCaptor.getValue().getId());
    }

    @Test
    void testDelete() {
        Long idParaBorrar = 1L;
        when(jugadorRepository.existsById(1L)).thenReturn(true);

        jugadorService.delete(idParaBorrar);

        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(jugadorRepository).deleteById(argumentCaptor.capture());
        assertEquals(idParaBorrar, argumentCaptor.getValue());
    }

    @Test
    void testDeleteNotFound() {
        Long idParaBorrar = 1L;
        when(jugadorRepository.existsById(1L)).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> jugadorService.delete(idParaBorrar));
    }

    @Test
    void testInsertExisting() {
        when(jugadorRepository.existsById(jugadorParaAgregar.getId())).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> jugadorService.save(jugadorParaAgregar));
    }

    @Test
    void testUpdateExisting() {
        when(jugadorRepository.existsById(jugadorParaAgregar.getId())).thenReturn(true);
        jugadorService.update(jugadorParaAgregar);
        ArgumentCaptor<Jugador> argumentCaptor = ArgumentCaptor.forClass(Jugador.class);
        verify(jugadorRepository).save(argumentCaptor.capture());
        assertEquals(jugadorParaAgregar.getId(), argumentCaptor.getValue().getId());
    }

    @Test
    void testUpdateNotFound() {
        when(jugadorRepository.existsById(jugadorParaAgregar.getId())).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> jugadorService.update(jugadorParaAgregar));
    }

    /*
            public Partido( Estado estado,
                            Jugador jugadorLocal,
                            Jugador jugadorVisitante,
                            int cantidadGamesLocal,
                            int cantidadGamesVisitante )
        */

    @Test
    void testCalculateRankingOneVictory() {
        JugadorDTO jugadorDTOToTest = jugadoresDTODePrueba.get(0);
        setLocalWinMatchs(1, jugadorDTOToTest);
        jugadorDTOToTest.setPartidosLocal(new HashSet<>(partidosDTODePruebaLocal));
        Jugador jugadorToTest = jugadorMapper.fromDTO(jugadorDTOToTest, new CycleAvoidingMappingContext());

        when(jugadorRepository.findById(jugadorToTest.getId())).thenReturn(Optional.of(jugadorToTest));
        when(jugadorRepository.existsById(jugadorToTest.getId())).thenReturn(true);

        jugadorService.calculateRankingById(jugadorDTOToTest.getId());
        ArgumentCaptor<Jugador> argumentCaptor = ArgumentCaptor.forClass(Jugador.class);
        verify(jugadorRepository).save(argumentCaptor.capture());

        assertEquals(10, argumentCaptor.getValue().getPuntos());
    }

    @Test
    void testCalculateRankingThreeLocalAndTwoVisitWins() {
        JugadorDTO jugadorDTOToTest = jugadoresDTODePrueba.get(0);
        setLocalWinMatchs(3, jugadorDTOToTest);
        jugadorDTOToTest.setPartidosLocal(new HashSet<>(partidosDTODePruebaLocal));
        setVisitWinMatchs(2, jugadorDTOToTest);
        jugadorDTOToTest.setPartidosVisita(new HashSet<>(partidosDTODePruebaVisita));

        Jugador jugadorToTest = jugadorMapper.fromDTO(jugadorDTOToTest, new CycleAvoidingMappingContext());

        when(jugadorRepository.findById(jugadorToTest.getId())).thenReturn(Optional.of(jugadorToTest));
        when(jugadorRepository.existsById(jugadorToTest.getId())).thenReturn(true);

        jugadorService.calculateRankingById(jugadorDTOToTest.getId());
        ArgumentCaptor<Jugador> argumentCaptor = ArgumentCaptor.forClass(Jugador.class);
        verify(jugadorRepository).save(argumentCaptor.capture());

        assertEquals(60, argumentCaptor.getValue().getPuntos());
    }

    @Test
    void testCalculateRankingTwoLocalLooses() {
        JugadorDTO jugadorDTOToTest = jugadoresDTODePrueba.get(1);
        setVisitWinMatchs(2, jugadoresDTODePrueba.get(0));
        jugadorDTOToTest.setPartidosLocal(new HashSet<>(partidosDTODePruebaVisita));
        Jugador jugadorToTest = jugadorMapper.fromDTO(jugadorDTOToTest, new CycleAvoidingMappingContext());

        when(jugadorRepository.findById(jugadorToTest.getId())).thenReturn(Optional.of(jugadorToTest));
        when(jugadorRepository.existsById(jugadorToTest.getId())).thenReturn(true);

        jugadorService.calculateRankingById(jugadorDTOToTest.getId());
        ArgumentCaptor<Jugador> argumentCaptor = ArgumentCaptor.forClass(Jugador.class);
        verify(jugadorRepository).save(argumentCaptor.capture());

        assertEquals(0, argumentCaptor.getValue().getPuntos());
    }

    @Test
    void testCalculateRankingTwoVisitLosses() {
        JugadorDTO jugadorDTOToTest = jugadoresDTODePrueba.get(1);
        setLocalWinMatchs(2, jugadoresDTODePrueba.get(0));
        jugadorDTOToTest.setPartidosVisita(new HashSet<>(partidosDTODePruebaLocal));
        Jugador jugadorToTest = jugadorMapper.fromDTO(jugadorDTOToTest, new CycleAvoidingMappingContext());

        when(jugadorRepository.findById(jugadorToTest.getId())).thenReturn(Optional.of(jugadorToTest));
        when(jugadorRepository.existsById(jugadorToTest.getId())).thenReturn(true);

        jugadorService.calculateRankingById(jugadorDTOToTest.getId());
        ArgumentCaptor<Jugador> argumentCaptor = ArgumentCaptor.forClass(Jugador.class);
        verify(jugadorRepository).save(argumentCaptor.capture());

        assertEquals(0, argumentCaptor.getValue().getPuntos());
    }

    @Test
    void testCalculateRankingSpecialCase() {
        JugadorDTO jugadorDTOToTest = jugadoresDTODePrueba.get(2);
        setLocalWinMatchs(2, jugadorDTOToTest);
        jugadorDTOToTest.setPartidosLocal(new HashSet<>(partidosDTODePruebaLocal));
        setVisitWinMatchs(1, jugadorDTOToTest);
        jugadorDTOToTest.setPartidosVisita(new HashSet<>(partidosDTODePruebaVisita));

        partidosDTODePruebaVisita.clear();
        partidosDTODePruebaLocal.clear();

        for (int i = 0; i < 2; i++) {
            jugadorDTOToTest.addPartidoVisita(new PartidoDTO(Estado.FINALIZADO,
                    jugadoresDTODePrueba.get(1), jugadorDTOToTest,
                    6, 2));
        }

        for (int i = 0; i < 3; i++) {
            jugadorDTOToTest.addPartidoLocal(new PartidoDTO(Estado.FINALIZADO,
                    jugadorDTOToTest, jugadoresDTODePrueba.get(0),
                    2, 6));
        }

        Jugador jugadorToTest = jugadorMapper.fromDTO(jugadorDTOToTest, new CycleAvoidingMappingContext());

        when(jugadorRepository.findById(jugadorToTest.getId())).thenReturn(Optional.of(jugadorToTest));
        when(jugadorRepository.existsById(jugadorToTest.getId())).thenReturn(true);

        jugadorService.calculateRankingById(jugadorDTOToTest.getId());
        ArgumentCaptor<Jugador> argumentCaptor = ArgumentCaptor.forClass(Jugador.class);
        verify(jugadorRepository).save(argumentCaptor.capture());

        assertEquals(20, argumentCaptor.getValue().getPuntos());
    }

    private void setVisitWinMatchs(int times, JugadorDTO winner) {
        for (int i = 0; i < times; i++) {
            partidosDTODePruebaVisita.add(new PartidoDTO(Estado.FINALIZADO,
                    jugadoresDTODePrueba.get(1), winner,
                    2, 6));
        }
    }

    private void setLocalWinMatchs(int times, JugadorDTO winner) {
        for (int i = 0; i < times; i++) {
            partidosDTODePruebaLocal.add(new PartidoDTO(Estado.FINALIZADO,
                    winner, jugadoresDTODePrueba.get(1),
                    6, 2));
        }
    }

}