package com.baufest.tennis.springtennis.dto;

import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.enums.ModoJugador;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PartidoDTOTest {

    private JugadorDTO localPlayer = new JugadorDTO();
    private JugadorDTO visitPlayer = new JugadorDTO();
    private PartidoDTO partidoDTO;

    @BeforeAll
    void beforeAll() {
        localPlayer.setNombre("JUGADOR LOCAL");
        visitPlayer.setNombre("JUGADOR VISITA");
    }

    @BeforeEach
    void beforeEach() {
        partidoDTO = new PartidoDTO(localPlayer, visitPlayer);
    }

    @Test
    void playMatchAndSetWinner() {
        do {
            if (Math.random() > Math.random()) partidoDTO.score(ModoJugador.LOCAL);
            else partidoDTO.score(ModoJugador.VISITANTE);

            MarcadorDTO local = partidoDTO.getLocalMarcadorDTO();
            MarcadorDTO visit = partidoDTO.getVisitMarcadorDTO();

            if (local.getScore() == 0 && visit.getScore() == 0)
                System.out.println("Local: " + local.getGames() + " - Visita: " + visit.getGames());
        } while (!partidoDTO.getState().equals(Estado.FINALIZADO));

        assertTrue(partidoDTO.getLocalMarcadorDTO().isWinner() || partidoDTO.getVisitMarcadorDTO().isWinner());
    }

    @Test
    void advantageCase() {
        partidoDTO.getVisitMarcadorDTO().setScore(45);
        partidoDTO.getLocalMarcadorDTO().setScore(45);
        partidoDTO.score(ModoJugador.LOCAL);

        assertTrue(partidoDTO.getLocalMarcadorDTO().isAdvantage());
    }

    @Test
    void advantageToEquals() {
        partidoDTO.getVisitMarcadorDTO().setScore(45);
        partidoDTO.getLocalMarcadorDTO().setScore(45);
        partidoDTO.getLocalMarcadorDTO().setAdvantage(true);
        partidoDTO.score(ModoJugador.VISITANTE);

        assertEquals(partidoDTO.getVisitMarcadorDTO().isAdvantage(), partidoDTO.getLocalMarcadorDTO().isAdvantage());
    }

}
