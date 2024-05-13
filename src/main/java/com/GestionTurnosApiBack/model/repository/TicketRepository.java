package com.GestionTurnosApiBack.model.repository;

import com.GestionTurnosApiBack.model.entity.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	List<Ticket> findByEstadoInOrderByFechaAsc(List<String> estados);
	List<Ticket> findByModuloIdAndEstadoOrderByIdDesc(Long moduloId, String estado);
}
