package com.GestionTurnosApiBack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GestionTurnosApiBack.model.entity.Modulo;
import com.GestionTurnosApiBack.model.entity.Ticket;
import com.GestionTurnosApiBack.model.services.ModuloService;
import com.GestionTurnosApiBack.model.services.TicketService;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = { "*" })
public class TicketController {

    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private ModuloService moduloService;

    @PostMapping
    public ResponseEntity<Ticket> generarTicket(@RequestBody Ticket ticket) {
    	
        Ticket nuevoTicket = ticketService.generarTicket(ticket);
        Modulo modulo  = this.moduloService.buscarPorId(nuevoTicket.getModulo().getId());
        nuevoTicket.setModulo(modulo);
        
    
        return new ResponseEntity<>(nuevoTicket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarPorId(@PathVariable Long id) {
        Ticket ticket = ticketService.buscarPorId(id);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/cambiar-estado")
    public ResponseEntity<Void> cambiarEstadoTicket(@PathVariable Long id, @RequestParam String nuevoEstado) {
        ticketService.cambiarEstadoTicket(id, nuevoEstado);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/listarTicketsModuloEstado/{moduloId}/{estado}")
    public ResponseEntity<List<Ticket>> listarTicketsPorEstadoYModulo(
            @PathVariable Long moduloId,
            @PathVariable String estado) {
        List<Ticket> tickets = ticketService.listarTicketsPorEstadoYModulo(moduloId, estado);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
