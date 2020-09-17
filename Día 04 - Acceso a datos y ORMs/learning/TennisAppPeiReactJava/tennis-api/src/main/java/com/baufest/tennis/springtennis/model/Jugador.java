package com.baufest.tennis.springtennis.model;

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

	@OneToMany(mappedBy = "jugadorLocal", orphanRemoval = true)
	private Set<Partido> partidosLocal = new HashSet<>();

	@OneToMany(mappedBy = "jugadorVisitante", orphanRemoval = true)
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

	public Set<Partido> getPartidosLocal() {
		return partidosLocal;
	}

	public void setPartidosLocal(Set<Partido> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}

	public void addPartidosLocal(Partido partidoLocal) {
		partidosLocal.add(partidoLocal);
		partidoLocal.setJugadorLocal(this);
	}

	public Set<Partido> getPartidosVisita() {
		return partidosVisita;
	}

	public void setPartidosVisita(Set<Partido> partidosVisita) {
		this.partidosVisita = partidosVisita;
	}

	public void addPartidosVisita(Partido partidoVisita) {
		partidosVisita.add(partidoVisita);
		partidoVisita.setJugadorVisitante(this);
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


	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("id",getId());
		jo.put("nombre",getNombre());
		jo.put("puntos",getPuntos());
		return jo;
	}

}
