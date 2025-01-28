package com.example.controllers;


import com.example.DTO.HotelDTO;
import com.example.entities.Hotel;
import com.example.services.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api")
@Tag(name="Hoteles", description = "Listado de hoteles")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hoteles")
    @Operation(summary = "Obtener todos los hoteles", description = "Obtiene una lista de todos los hoteles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de hoteles obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "No se encontraron hoteles")
    })
    public List<Hotel> getAll(){
        try {
            return hotelService.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener todos los hoteles", e);
        }
    }

    @GetMapping("/hotel/localidad/{localidad}")
    @Operation(summary = "Obtener hotel por localidad", description = "Obtiene un hotel por su localidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    })
    public ResponseEntity<List<Hotel>> getBylocalidad(@PathVariable @Parameter(description = "Localidad del hotel", example = "Londres") String localidad) {
        try {
            return ResponseEntity.ok(hotelService.findBylocalidad(localidad));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener el hotel por localidad", e);
        }
    }

    @GetMapping("/hotel/categoria/{categoria}")
    @Operation(summary = "Obtener hotel por categoria", description = "Obtiene un hotel por su categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    })
    /*3 ESTRELLAS, por ejemplo*/
    public ResponseEntity<List<Hotel>> getBycategoria(@PathVariable @Parameter(description = "Categoria del hotel", example = "3_ESTRELLAS") String categoria) {
        try {
            return ResponseEntity.ok(hotelService.findByCategoria(categoria));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener el hotel por categoria", e);
        }
    }


    @PostMapping("saveHotel")
    @Operation(summary = "Guardar un nuevo hotel", description = "Guarda un nuevo hotel en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    /*{
        "nombre": "string",
            "descripcion": "string",
            "categoria": "string",
            "piscina": true,
            "localidad": "string"
    }*/
        public ResponseEntity<Hotel> saveHotel(@RequestBody @Parameter(description = "Datos del hotel a guardar") HotelDTO hotel) {
        try {
            hotelService.guardarHotel(hotel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solicitud incorrecta al guardar el nuevo hotel", e);
        }
    }
}
