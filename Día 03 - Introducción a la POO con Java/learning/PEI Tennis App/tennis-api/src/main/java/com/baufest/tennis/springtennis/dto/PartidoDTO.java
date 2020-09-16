package com.baufest.tennis.springtennis.dto;

import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.enums.ModoJugador;

import java.time.LocalDateTime;

public class PartidoDTO {

    private Long id;
    private LocalDateTime startTime, endTime;
    private Estado state;
    private MarcadorDTO localMarcadorDTO, visitMarcadorDTO;

    public PartidoDTO(JugadorDTO localPlayer, JugadorDTO visitPlayer) {
        this.localMarcadorDTO = new MarcadorDTO(ModoJugador.LOCAL, localPlayer);
        this.visitMarcadorDTO = new MarcadorDTO(ModoJugador.VISITANTE, visitPlayer);
        this.state = Estado.NO_INICIADO;
    }

    public void startMatch() {
        startTime = LocalDateTime.now();
        state = Estado.EN_CURSO;
    }

    public void endMatch() {
        endTime = LocalDateTime.now();
        state = Estado.FINALIZADO;
    }

    private void game(MarcadorDTO marcadorDTO) {
        marcadorDTO.addGame();
        visitMarcadorDTO.resetScore();
        localMarcadorDTO.resetScore();

        // Just for a simple game
        if (marcadorDTO.getGames() == 6) endMatch(marcadorDTO);
    }

    private void endMatch(MarcadorDTO winner) {
        winner.winGame();
        if (winner.getModoJugador().equals(ModoJugador.LOCAL)) visitMarcadorDTO.loseGame();
        else localMarcadorDTO.loseGame();

        endMatch();
    }

    public void score(ModoJugador modoJugador) {
        if (modoJugador.equals(ModoJugador.LOCAL)) {
            if (localMarcadorDTO.getScore() < 45) localMarcadorDTO.addScore();
            else if (localMarcadorDTO.isAdvantage()) game(localMarcadorDTO);
            else if (visitMarcadorDTO.isAdvantage()) visitMarcadorDTO.advantageOut();
            else if (visitMarcadorDTO.getScore() == 45) {
                localMarcadorDTO.advantageIn();
            } else game(localMarcadorDTO);
        } else {
            if (visitMarcadorDTO.getScore() < 45) visitMarcadorDTO.addScore();
            else if (visitMarcadorDTO.isAdvantage()) game(visitMarcadorDTO);
            else if (localMarcadorDTO.isAdvantage()) localMarcadorDTO.advantageOut();
            else if (localMarcadorDTO.getScore() == 45) {
                visitMarcadorDTO.advantageIn();
            } else game(visitMarcadorDTO);
        }
        modoJugador.equals(1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Estado getState() {
        return state;
    }

    public void setState(Estado state) {
        this.state = state;
    }

    public MarcadorDTO getLocalMarcadorDTO() {
        return localMarcadorDTO;
    }

    public void setLocalMarcadorDTO(MarcadorDTO localMarcadorDTO) {
        this.localMarcadorDTO = localMarcadorDTO;
    }

    public MarcadorDTO getVisitMarcadorDTO() {
        return visitMarcadorDTO;
    }

    public void setVisitMarcadorDTO(MarcadorDTO visitMarcadorDTO) {
        this.visitMarcadorDTO = visitMarcadorDTO;
    }

    public JugadorDTO getLocalPlayer() {
        return localMarcadorDTO.getPlayer();
    }

    public JugadorDTO getVisitPlayer() {
        return visitMarcadorDTO.getPlayer();
    }

    @Override
    public String toString() {
        return "PartidoDTO{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", state=" + state +
                ", localMarcadorDTO=" + localMarcadorDTO +
                ", visitMarcadorDTO=" + visitMarcadorDTO +
                '}';
    }
}
