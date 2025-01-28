package com.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tamanio")
    private int tamanio;

    @Column(name = "precio")
    private int precio;

    @Column(name = "desayuno")
    private boolean desayuno;

    @Column(name = "ocupada")
    private boolean ocupada;

    @ManyToOne
    @JoinColumn(name = "id_hotel", referencedColumnName="id")
    @JsonBackReference
    private Hotel hotel;

    public Habitacion() {
    }

    public Habitacion(int id, int tamanio, int precio, boolean desayuno, boolean ocupada, Hotel hotel) {
        this.id = id;
        this.tamanio = tamanio;
        this.precio = precio;
        this.desayuno = desayuno;
        this.ocupada = ocupada;
        this.hotel = hotel;
    }

    public Habitacion(int tamanio, int precio, boolean desayuno, boolean ocupada) {
        this.tamanio = tamanio;
        this.precio = precio;
        this.desayuno = desayuno;
        this.ocupada = ocupada;
    }

    public Habitacion(int id, int tamanio, int precio, boolean desayuno, boolean ocupada) {
        this.id = id;
        this.tamanio = tamanio;
        this.precio = precio;
        this.desayuno = desayuno;
        this.ocupada = ocupada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isDesayuno() {
        return desayuno;
    }

    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", tamanio=" + tamanio +
                ", precio=" + precio +
                ", desayuno=" + desayuno +
                ", ocupada=" + ocupada +
                ", hotel=" + hotel +
                '}';
    }
}
