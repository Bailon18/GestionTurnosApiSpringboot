package com.GestionTurnosApiBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GestionTurnosApiBack.Utils.ModuloTicketDTO;
import com.GestionTurnosApiBack.model.entity.Modulo;
import com.GestionTurnosApiBack.model.services.ModuloService;

import java.util.List;

@RestController
@RequestMapping("/modulos")
@CrossOrigin(origins = { "*" })
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> buscarPorId(@PathVariable Long id) {
        Modulo modulo = moduloService.buscarPorId(id);
        if (modulo != null) {
            return new ResponseEntity<>(modulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Modulo> guardar(@RequestBody Modulo modulo) {
        Modulo nuevoModulo = moduloService.guardar(modulo);
        return new ResponseEntity<>(nuevoModulo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Modulo>> listarTodos() {
        List<Modulo> modulos = moduloService.listarTodos();
        return new ResponseEntity<>(modulos, HttpStatus.OK);
    }

    @PostMapping("/desabilitar/{id}/{activo}")
    public ResponseEntity<?> cambiarEstadoModulo(@PathVariable Long id, @PathVariable boolean activo) {
        moduloService.cambiarEstadoModulo(id, activo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Modulo> actualizar(@PathVariable Long id, @RequestBody Modulo moduloActualizado) {
        Modulo moduloActualizado2 = moduloService.actualizarModulo(id, moduloActualizado);
        if (moduloActualizado2 != null) {
            return new ResponseEntity<>(moduloActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/buscarServicio/{idServicio}")
    public ResponseEntity<List<ModuloTicketDTO>> getTotalTicketsByServicio(@PathVariable Long idServicio) {
        List<ModuloTicketDTO> moduloTickets = moduloService.getTotalTicketsByServicioo(idServicio);
        return new ResponseEntity<>(moduloTickets, HttpStatus.OK);
    }

}
