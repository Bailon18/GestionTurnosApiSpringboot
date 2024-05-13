package com.GestionTurnosApiBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.GestionTurnosApiBack.model.entity.Servicio;
import com.GestionTurnosApiBack.model.services.ServicioService;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/servicios")
@CrossOrigin(origins = { "*" })
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> buscarPorId(@PathVariable Long id) {
        Servicio servicio = servicioService.buscarPorId(id);
        if (servicio != null) {
            return new ResponseEntity<>(servicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Servicio> guardar(
    		@RequestPart("servicio") Servicio servicio,
    		@RequestPart(name = "imagen", required = false) MultipartFile imagen
    		) throws IOException {

        if (imagen != null && !imagen.isEmpty()) {
            byte[] imagenBytes = imagen.getBytes();
            servicio.setImagen(imagenBytes);
        }
        
        Servicio nuevoServicio = servicioService.guardar(servicio);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }
    
   
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Servicio> actualizar(
            @PathVariable Long id,
            @RequestPart("servicio") Servicio servicio,
            @RequestPart(name = "imagen", required = false) MultipartFile imagen
    ) throws IOException {

        System.out.println("IMAGEN: "+ imagen);

        if (imagen != null && !imagen.isEmpty()) {
            byte[] imagenBytes = imagen.getBytes();
            servicio.setImagen(imagenBytes);
        }

        Servicio servicioActualizado = servicioService.actualizar(id, servicio);
        if (servicioActualizado != null) {
            return new ResponseEntity<>(servicioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Servicio>> listarTodos() {
        List<Servicio> servicios = servicioService.listarTodos();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @PostMapping("/cambiar-estado/{id}/{estado}")
    public ResponseEntity<?> deshabilitarServicio(@PathVariable Long id, @PathVariable boolean estado) {
        System.out.println("LLEGO!");
        try {
            servicioService.deshabilitarServicio(id, estado);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servicio no encontrado");
        }

    }
}
