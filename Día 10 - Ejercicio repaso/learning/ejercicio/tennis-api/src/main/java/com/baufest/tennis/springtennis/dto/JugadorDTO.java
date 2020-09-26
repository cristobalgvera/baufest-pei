package com.baufest.tennis.springtennis.dto;

import com.baufest.tennis.springtennis.model.Partido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"partidosLocal", "partidosVisita"})
public class JugadorDTO {

	private Long id;

	private String nombre;

	private int puntos;

	@JsonProperty("partidosLocal")
	private Set<PartidoDTO> partidosLocal = new HashSet<>();

	@JsonProperty("partidosVisita")
	private Set<PartidoDTO> partidosVisita = new HashSet<>();

	public JugadorDTO(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public JugadorDTO(Long id, String nombre, int puntos) {
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public JugadorDTO(Long id, String nombre, int puntos, Set<PartidoDTO> partidosLocal, Set<PartidoDTO> partidosVisita) {
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
		this.partidosLocal = partidosLocal;
		this.partidosVisita = partidosVisita;
	}

	public JugadorDTO(){}

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

	public Set<PartidoDTO> getPartidosLocal() {
		return partidosLocal;
	}

	public void setPartidosLocal(Set<PartidoDTO> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}

	public void addPartidoLocal(PartidoDTO partido) {
		partidosLocal.add(partido);
		partido.setJugadorLocal(this);
	}

	public void addPartidosLocal(Set<PartidoDTO> partidosLocal) {
		partidosLocal.addAll(partidosLocal);
	}

	public Set<PartidoDTO> getPartidosVisita() {
		return partidosVisita;
	}

	public void setPartidosVisita(Set<PartidoDTO> partidosVisita) {
		this.partidosVisita = partidosVisita;
	}

	public void addPartidoVisita(PartidoDTO partido) {
		partidosVisita.add(partido);
		partido.setJugadorVisitante(this);
	}

	public void addPartidosVisita(Set<PartidoDTO> partidosVisita) {
		partidosVisita.addAll(partidosVisita);
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
