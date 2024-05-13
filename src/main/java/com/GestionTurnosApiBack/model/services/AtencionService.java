package com.GestionTurnosApiBack.model.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import com.GestionTurnosApiBack.model.entity.*;

import reactor.core.publisher.Flux;

public interface AtencionService {
	
	void iniciarAtencion(Long idAtencion);
	void finalizarAtencion(Long idAtencion, Timestamp fechafinal);
	List<Ticket> obtenerTicketsPendientesYEnAtencionOrdenadosPorFecha();
	void cancelarAtencion(Long idAtencion);
	Atencion guardarAtencion(Atencion atencion);
	void llamarCliente(Long idTicket, String estado);
	List<Atencion> listarAtenciones();
}
