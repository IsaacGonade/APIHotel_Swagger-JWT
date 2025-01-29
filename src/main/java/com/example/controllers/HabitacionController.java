package com.example.controllers;

import com.example.DTO.HabitacionDTO;
import com.example.entities.Habitacion;
import com.example.entities.Hotel;
import com.example.services.HabitacionService;
import com.example.services.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name="Habitaciones", description = "Listado de habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    private final HotelService hotelService;


    public HabitacionController(HabitacionService habitacionService, HotelService hotelService) {
        this.habitacionService = habitacionService;
        this.hotelService = hotelService;
    }


    //ruta para listar todas las habitaciones
    @GetMapping("/habitaciones")
    @Operation(summary = "Obtener todos las habitaciones", description = "Obtiene una lista de todos las habitaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de habitaciones obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "No se encontraron habitaciones")
    })
    public List<Habitacion> getAll(){
        try {
            return habitacionService.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener todos las habitaciones", e);
        }
    }

    //ruta para buscar habitaciones en un rango de capacidad y precio
    @GetMapping("/habitaciones/buscar")
    @Operation(summary = "Obtener todas las habitaciones por un rango", description = "Obtiene una lista de todos las habitaciones")
    public List<Habitacion> buscarHabitaciones(@RequestParam int tamanio, @RequestParam int precioMin, @RequestParam int precioMax) {
        // Llamamos al repositorio para realizar la búsqueda
        return habitacionService.findByTamanioAndPrecio(tamanio, precioMin, precioMax);
    }


    //ruta para guardar una nueva habitacion
    @PostMapping("/hotel/{id_hotel}/saveHabitacion")
    @Operation(summary = "Crear una nueva habitacion en un hotel")
    /*{
        "tamanio": 0,
            "precio": 0,
            "desayuno": true,
            "ocupada": true
    }*/
    public ResponseEntity<?> saveHabitacion(@RequestBody HabitacionDTO habitacionDTO, @PathVariable int id_hotel) {
        Habitacion habitacion = new Habitacion();
        habitacion.setTamanio(habitacionDTO.tamanio());
        habitacion.setPrecio(habitacionDTO.precio());
        habitacion.setDesayuno(habitacionDTO.desayuno());
        habitacion.setOcupada(habitacionDTO.ocupada());
        try {
            Optional<Hotel> hotel = hotelService.findById(id_hotel);
            if (hotel.isPresent()){
                var hotelEncontrado = hotel.get();
                hotelEncontrado.addHabitacion(habitacion);
                habitacionService.guardarHabitacion(habitacion);
            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solicitud incorrecta al guardar la nueva habitacion", e);
        }
    }


    //ruta para borrar una habitacion
    @DeleteMapping("/habitaciones/borrar/{id_habitacion}")
    @Operation(summary = "Borrar una habitación")
    public ResponseEntity<?> deleteHabitacion(@PathVariable int id_habitacion){
        habitacionService.borrarHabitacion(id_habitacion);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //ruta para modificar el esatdo de ocupacion de una habitacion
    @PutMapping("/habitaciones/editar/{id_habitacion}")
    @Operation(summary = "Modificar ocupación de una habitación")
    public ResponseEntity<?> updateHabitacion(@PathVariable int id_habitacion){
        Habitacion habitacion = habitacionService.findById(id_habitacion).get();

        habitacionService.modificarHabitacion(habitacion);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
