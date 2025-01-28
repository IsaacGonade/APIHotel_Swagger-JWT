package com.example.DTO;

import com.example.entities.Habitacion;

import java.util.List;

public record HotelDTO(String nombre, String descripcion, String categoria, boolean piscina, String localidad) {
}
