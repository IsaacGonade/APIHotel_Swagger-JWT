package com.example.repositories;

import com.example.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByLocalidad(String localidad);

    List<Hotel> findByCategoria(String categoria);
}
