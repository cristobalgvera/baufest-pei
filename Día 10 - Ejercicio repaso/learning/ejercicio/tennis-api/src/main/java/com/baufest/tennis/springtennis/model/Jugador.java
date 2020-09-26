package com.baufest.tennis.springtennis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Jugador")
public class Jugador{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private int puntos;

	@OneToMany(mappedBy = "jugadorLocal", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonProperty("partidosLocal")
	private Set<Partido> partidosLocal = new HashSet<>();

	@OneToMany(mappedBy = "jugadorVisitante", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonProperty("partidosVisita")
	private Set<Partido> partidosVisita = new HashSet<>();

	public Jugador(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public Jugador(Long id, String nombre, int puntos) {
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public Jugador(Long id, String nombre, int puntos, Set<Partido> partidosLocal, Set<Partido> partidosVisita) {
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
		this.partidosLocal = partidosLocal;
		this.partidosVisita = partidosVisita;
	}

	public Jugador(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Set<Partido> getPartidosLocal() {
		return partidosLocal;
	}

	public void setPartidosLocal(Set<Partido> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}

	public void addPartidoLocal(Partido partido) {
		partidosLocal.add(partido);
		partido.setJugadorLocal(this);
	}

	public Set<Partido> getPartidosVisita() {
		return partidosVisita;
	}

	public void setPartidosVisita(Set<Partido> partidosVisita) {
		this.partidosVisita = partidosVisita;
	}

	public void addPartidoVisita(Partido partido) {
		partidosVisita.add(partido);
		partido.setJugadorVisitante(this);
	}

	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("id",getId());
		jo.put("nombre",getNombre());
		jo.put("puntos",getPuntos());
//		jo.put("partidosLocal", getPartidosLocal());
//		jo.put("partidosVisita", getPartidosVisita());
		return jo;
	}

}
