package com.example.services;

import com.example.entities.Habitacion;
import com.example.repositories.HabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;


    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public List<Habitacion> findAll(){
        /*List<HabitacionDTO> habitacionDTOS = new ArrayList<>();
        habitacionRepository.findAll().forEach(habitacion ->
                habitacionDTOS.add(new HabitacionDTO(habitacion.getId(), habitacion.getTamanio(), habitacion.getPrecio(), habitacion.isDesayuno(), habitacion.isOcupada(), habitacion.getId_hotel())));
        return habitacionDTOS;*/

        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> findById(int id_habitacion){
        return habitacionRepository.findById(id_habitacion);
    }


    public List<Habitacion> findByTamanioAndPrecio(int tamanio, int precioMin, int precioMax){
        /*List<HotelDTOID> hotelesDTOID = new ArrayList<>();
        *//*hotelRepository.findByCategoria(categoria).forEach(hotel ->
                hotelesDTOID.add(new HotelDTOID(hotel.getId(), hotel.getNombre(), hotel.getDescripcion(), hotel.getCategoria(), hotel.isPiscina(), hotel.getLocalidad())));
        return hotelesDTOID;*/

        List<Habitacion> habitaciones = habitacionRepository.findHabitacionesByHotelYPrecioYTamano(tamanio, precioMin, precioMax);
        return habitaciones;
    }


    public void guardarHabitacion(Habitacion habitacion){
        habitacionRepository.save(habitacion);
    }

    public void borrarHabitacion(int id_habitacion){
        habitacionRepository.deleteById(id_habitacion);
    }

    public Habitacion modificarHabitacion(Habitacion habitacion){
        if (habitacion.isOcupada()){
            habitacion.setOcupada(false);
        } else {
            habitacion.setOcupada(true);
        }
        return habitacionRepository.save(habitacion);
    }
}
