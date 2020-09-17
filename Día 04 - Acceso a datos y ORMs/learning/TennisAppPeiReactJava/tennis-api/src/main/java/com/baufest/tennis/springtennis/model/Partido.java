package com.baufest.tennis.springtennis.model;

import com.baufest.tennis.springtennis.enums.Estado;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Partido {

	@Id
	@GeneratedValue(generator = "partido_sequence")
	@Column(name = "id_partido")
	private Long id;

	@Column(name = "fechacomienzo")
	private Date fechaComienzo;

	private Estado estado;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "id_jugador_local")
	private Jugador jugadorLocal;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "id_jugador_visita")
	private Jugador jugadorVisitante;

	private int scoreLocal;

	private String puntosGameActualLocal;


	private int cantidadGamesLocal;

	private int scoreVisitante;


	private String puntosGameActualVisitante;


	private int cantidadGamesVisitante;


	public Partido(Date fechaComienzo, Estado estado, Jugador jugadorLocal, Jugador jugadorVisitante) {
		this.fechaComienzo = fechaComienzo;
		this.estado = estado;
		this.jugadorLocal = jugadorLocal;
		this.jugadorVisitante = jugadorVisitante;
		this.scoreLocal = 0;
		this.puntosGameActualLocal = "0";
		this.cantidadGamesLocal = 0;
		this.scoreVisitante = 0;
		this.puntosGameActualVisitante = "0";
		this.cantidadGamesVisitante = 0;
	}

	public Partido(Long id, Date fechaComienzo, Estado estado, Jugador jugadorLocal, Jugador jugadorVisitante, int scoreLocal, int cantidadGamesLocal, int scoreVisitante, int cantidadGamesVisitante) {
		this.id = id;
		this.fechaComienzo = fechaComienzo;
		this.estado = estado;
		this.jugadorLocal = jugadorLocal;
		this.jugadorVisitante = jugadorVisitante;
		this.scoreLocal = scoreLocal;
		this.cantidadGamesLocal = cantidadGamesLocal;
		this.scoreVisitante = scoreVisitante;
		this.cantidadGamesVisitante = cantidadGamesVisitante;
	}

	public Partido(Date fechaComienzo, Estado estado, Jugador jugadorLocal, Jugador jugadorVisitante, int scoreLocal,
				   String puntosGameActualLocal, int cantidadGamesLocal, int scoreVisitante, String puntosGameActualVisitante,
				   int cantidadGamesVisitante) {
		this.fechaComienzo = fechaComienzo;
		this.estado = estado;
		this.jugadorLocal = jugadorLocal;
		this.jugadorVisitante = jugadorVisitante;
		this.scoreLocal = scoreLocal;
		this.puntosGameActualLocal = puntosGameActualLocal;
		this.cantidadGamesLocal = cantidadGamesLocal;
		this.scoreVisitante = scoreVisitante;
		this.puntosGameActualVisitante = puntosGameActualVisitante;
		this.cantidadGamesVisitante = cantidadGamesVisitante;
	}

	public Partido() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaComienzo() {
		return fechaComienzo;
	}

	public void setFechaComienzo(Date fechaComienzo) {
		this.fechaComienzo = fechaComienzo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Jugador getJugadorLocal() {
		return jugadorLocal;
	}

	public void setJugadorLocal(Jugador jugadorLocal) {
		this.jugadorLocal = jugadorLocal;
	}

	public Jugador getJugadorVisitante() {
		return jugadorVisitante;
	}

	public void setJugadorVisitante(Jugador jugadorVisitante) {
		this.jugadorVisitante = jugadorVisitante;
	}

	public int getScoreLocal() {
		return scoreLocal;
	}

	public void setScoreLocal(int scoreLocal) {
		this.scoreLocal = scoreLocal;
	}

	public String getPuntosGameActualLocal() {
		return puntosGameActualLocal;
	}

	public void setPuntosGameActualLocal(String puntosGameActualLocal) {
		this.puntosGameActualLocal = puntosGameActualLocal;
	}

	public int getCantidadGamesLocal() {
		return cantidadGamesLocal;
	}

	public void setCantidadGamesLocal(int cantidadGamesLocal) {
		this.cantidadGamesLocal = cantidadGamesLocal;
	}

	public int getScoreVisitante() {
		return scoreVisitante;
	}

	public void setScoreVisitante(int scoreVisitante) {
		this.scoreVisitante = scoreVisitante;
	}

	public String getPuntosGameActualVisitante() {
		return puntosGameActualVisitante;
	}

	public void setPuntosGameActualVisitante(String puntosGameActualVisitante) {
		this.puntosGameActualVisitante = puntosGameActualVisitante;
	}

	public int getCantidadGamesVisitante() {
		return cantidadGamesVisitante;
	}

	public void setCantidadGamesVisitante(int cantidadGamesVisitante) {
		this.cantidadGamesVisitante = cantidadGamesVisitante;
	}



	@Override
	public String toString() {
		return "partido [id=" + id + ", fechaComienzo=" + fechaComienzo + ", estado=" + estado + ", jugadorLocal="
				+ jugadorLocal + ", jugadorVisitante=" + jugadorVisitante + ", scoreLocal=" + scoreLocal
				+ ", puntosGameActualLocal=" + puntosGameActualLocal + ", cantidadGamesLocal=" + cantidadGamesLocal
				+ ", scoreVisitante=" + scoreVisitante + ", puntosGameActualVisitante=" + puntosGameActualVisitante
				+ ", cantidadGamesVisitante=" + cantidadGamesVisitante + "]";
	}

	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("id", getId());
		jo.put("fechaComienzo", getFechaComienzo());
		jo.put("estado", getEstado());
		jo.put("jugadorLocal",getJugadorLocal());
		jo.put("jugadorVisitante",getJugadorVisitante());
		jo.put("scoreLocal", getScoreLocal());
		jo.put("puntosGameActualLocal", getPuntosGameActualLocal());
		jo.put("cantidadGamesLocal", getCantidadGamesLocal());
		jo.put("scoreVisitante", getScoreVisitante());
		jo.put("puntosGameActualVisitante", getPuntosGameActualVisitante());
		jo.put("cantidadGamesVisitante", getCantidadGamesVisitante());
		return jo;
	}
}
