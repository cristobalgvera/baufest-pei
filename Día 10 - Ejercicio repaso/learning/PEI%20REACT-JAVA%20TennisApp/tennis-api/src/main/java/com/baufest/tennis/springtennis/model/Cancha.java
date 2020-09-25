package com.baufest.tennis.springtennis.model;

import org.json.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre, direccion;

    @OneToMany(mappedBy = "cancha", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Partido> partidos = new HashSet<>();

    public Cancha() {
    }

    public Cancha(Long id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Cancha(Long id, String nombre, String direccion, Set<Partido> partidos) {
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

    public Set<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(Set<Partido> partidos) {
        this.partidos = partidos;
    }

    public void addPartido(Partido partido) {
        partidos.add(partido);
        partido.setCancha(this);
    }

    public void deletePartido(Partido partido) {
        partidos.remove(partido);
        partido.setCancha(null);
    }

    @Override
    public String toString() {
        return "Cancha{" +
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
