package com.baufest.tennis.springtennis.dto;

import com.baufest.tennis.springtennis.enums.ModoJugador;

public class MarcadorDTO {

    private ModoJugador modoJugador;
    private boolean advantage, winner;
    private JugadorDTO player;
    private int score, games;

    public MarcadorDTO(ModoJugador modoJugador, JugadorDTO player) {
        this.modoJugador = modoJugador;
        this.player = player;
    }

    public MarcadorDTO(ModoJugador modoJugador, JugadorDTO player, int games) {
        this.modoJugador = modoJugador;
        this.player = player;
        this.games = games;
    }

    public void resetScore() {
        advantage = false;
        score = 0;
    }

    public ModoJugador getModoJugador() {
        return modoJugador;
    }

    public void setModoJugador(ModoJugador modoJugador) {
        this.modoJugador = modoJugador;
    }

    public JugadorDTO getPlayer() {
        return player;
    }

    public void setPlayer(JugadorDTO player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore() {
        this.score += 15;
    }

    public void advantageIn() {
        advantage = true;
    }

    public void advantageOut() {
        advantage = false;
    }

    public boolean isAdvantage() {
        return advantage;
    }

    public void setAdvantage(boolean advantage) {
        this.advantage = advantage;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void addGame() {
        games += 1;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public void winGame() {
        winner = true;
    }

    public void loseGame() {
        winner = false;
    }

    @Override
    public String toString() {
        return "MarcadorDTO{" +
                "modoJugador=" + modoJugador +
                ", advantage=" + advantage +
                ", winner=" + winner +
                ", player=" + player +
                ", score=" + score +
                ", games=" + games +
                '}';
    }
}
