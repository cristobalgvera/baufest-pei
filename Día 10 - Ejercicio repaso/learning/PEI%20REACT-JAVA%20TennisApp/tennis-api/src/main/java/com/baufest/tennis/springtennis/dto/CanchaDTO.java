package com.baufest.tennis.springtennis.dto;

import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class CanchaDTO {

    private Long id;
    private String nombre, direccion;
    private Set<PartidoDTO> partidos = new HashSet<>();

    public CanchaDTO() {
    }

    public CanchaDTO(Long id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public CanchaDTO(Long id, String nombre, String direccion, Set<PartidoDTO> partidos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.partidos = partidos;
    }

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<PartidoDTO> getPartidos() {
        return partidos;
    }

    public void setPartidos(Set<PartidoDTO> partidos) {
        this.partidos = partidos;
    }

    public void addPartido(PartidoDTO partidoDTO) {
        partidos.add(partidoDTO);
        partidoDTO.setCancha(this);
    }

    public void deletePartido(PartidoDTO partidoDTO) {
        partidos.add(partidoDTO);
        partidoDTO.setCancha(null);
    }

    @Override
    public String toString() {
        return "CanchaDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
//                ", partidos=" + partidos +
                '}';
    }

    public JSONObject toJSONObject() {
        JSONObject jo = new JSONObject();
        jo.put("id", id);
        jo.put("nombre", nombre);
        jo.put("direccion", direccion);
        jo.put("partidos", partidos);
        return jo;
    }

}
