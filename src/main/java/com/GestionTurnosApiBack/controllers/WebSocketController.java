package com.GestionTurnosApiBack.controllers;

import com.GestionTurnosApiBack.model.entity.Ticket;
import com.GestionTurnosApiBack.model.services.AtencionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private AtencionService atencionService;

    @MessageMapping("/llamar")
    @SendTo("/topic/llamar")
    public Ticket llamarcliente(Ticket ticket){
        logger.info("LLEGO: {}", ticket);
        return new Ticket(ticket.getId(),ticket.getNumeroTicket(), ticket.getEstado(), ticket.getCliente(),
                ticket.getServicio(), ticket.getModulo());
    }

}

