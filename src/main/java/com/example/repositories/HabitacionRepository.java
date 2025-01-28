package com.example.repositories;


import com.example.entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabitacionRepository  extends JpaRepository<Habitacion, Integer> {
    @Query("SELECT h FROM Habitacion h WHERE h.precio BETWEEN :precioMin AND :precioMax AND h.tamanio IN :tamanio AND h.ocupada = false")
    List<Habitacion> findHabitacionesByHotelYPrecioYTamano(@Param("tamanio") int tamanio, @Param("precioMin") int precioMin, @Param("precioMax") int precioMax);

}

