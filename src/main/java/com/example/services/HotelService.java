package com.example.services;

import com.example.DTO.HotelDTO;
import com.example.entities.Hotel;
import com.example.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService{
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAll(){
        /*List<HotelDTOID> hotelesDTOID = new ArrayList<>();
        hotelRepository.findAll().forEach(hotel ->
                hotelesDTOID.add(new HotelDTOID(hotel.getId(), hotel.getNombre(), hotel.getDescripcion(), hotel.getCategoria(), hotel.isPiscina(), hotel.getLocalidad())));
        return hotelesDTOID;*/
        return hotelRepository.findAll();
    }

    public Optional<Hotel> findById(int id_hotel){
        return hotelRepository.findById(id_hotel);
    }

    public void guardarHotel(HotelDTO hotelDTO){
        Hotel hotel = new Hotel(hotelDTO.nombre(), hotelDTO.descripcion(), hotelDTO.categoria(), hotelDTO.piscina(), hotelDTO.localidad());
        hotelRepository.save(hotel);
    }

    public List<Hotel> findBylocalidad(String localidad){
        /*List<HotelDTOID> hotelesDTOID = new ArrayList<>();
        hotelRepository.findByLocalidad(localidad).forEach(hotel ->
                hotelesDTOID.add(new HotelDTOID(hotel.getId(), hotel.getNombre(), hotel.getDescripcion(), hotel.getCategoria(), hotel.isPiscina(), hotel.getLocalidad())));
        return hotelesDTOID;*/

        return hotelRepository.findByLocalidad(localidad);
    }

    public List<Hotel> findByCategoria(String categoria){
        /*List<HotelDTOID> hotelesDTOID = new ArrayList<>();
        hotelRepository.findByCategoria(categoria).forEach(hotel ->
                hotelesDTOID.add(new HotelDTOID(hotel.getId(), hotel.getNombre(), hotel.getDescripcion(), hotel.getCategoria(), hotel.isPiscina(), hotel.getLocalidad())));
        return hotelesDTOID;*/

        return hotelRepository.findByCategoria(categoria);
    }
}
