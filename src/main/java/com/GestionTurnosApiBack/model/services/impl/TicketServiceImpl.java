package com.GestionTurnosApiBack.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestionTurnosApiBack.model.entity.Ticket;
import com.GestionTurnosApiBack.model.repository.TicketRepository;
import com.GestionTurnosApiBack.model.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket generarTicket(Ticket ticket) {
        ticket = ticketRepository.save(ticket);
        String nuevoNumeroTicket = "BNP-" + String.format("%05d", ticket.getId());
        ticket.setNumeroTicket(nuevoNumeroTicket);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket buscarPorId(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public void cambiarEstadoTicket(Long id, String nuevoEstado) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            ticket.setEstado(nuevoEstado);
            ticketRepository.save(ticket);
        }
    }
    
    @Override
    public List<Ticket> listarTicketsPorEstadoYModulo(Long moduloId, String estado) {
        return ticketRepository.findByModuloIdAndEstadoOrderByIdDesc(moduloId, estado);
    }
}
