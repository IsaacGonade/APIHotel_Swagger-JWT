package com.example.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Schema(description = "Identificador de Hotel", example = "3", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "Nombre del hotel", example = "NH", required = true)
    @Column(name = "nombre")
    private String nombre;

    @Schema(description = "Descripcion dell hotel", required = true)
    @Column(name = "descripcion")
    private String descripcion;

    @Schema(description = "Categoria del hotel", example = "3 estrellas", required = true)
    @Column(name = "categoria")
    private String categoria;

    @Schema(description = "Tiene piscina", example = "SI", required = true)
    @Column(name = "piscina")
    private boolean piscina;

    @Schema(description = "Localidad del hotel", example = "Rio de janeiro", required = true)
    @Column(name = "localidad")
    private String localidad;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Habitacion> habitaciones;


    public Hotel() {
    }

    public Hotel(int id, String nombre, String descripcion, String categoria, boolean piscina, String localidad, List<Habitacion> habitaciones) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.piscina = piscina;
        this.localidad = localidad;
        this.habitaciones = habitaciones;
    }

    public Hotel(String nombre, String descripcion, String categoria, boolean piscina, String localidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.piscina = piscina;
        this.localidad = localidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String description) {
        this.descripcion = description;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isPiscina() {
        return piscina;
    }

    public void setPiscina(boolean piscina) {
        this.piscina = piscina;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void addHabitacion(Habitacion habitacion) {
        this.getHabitaciones().add(habitacion);
        habitacion.setHotel(this);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", piscina=" + piscina +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
