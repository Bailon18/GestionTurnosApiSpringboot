package com.GestionTurnosApiBack.model.repository;

import com.GestionTurnosApiBack.model.entity.Atencion;
import com.GestionTurnosApiBack.model.entity.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AtencionRepository extends JpaRepository<Atencion, Long> {
   
	 @Query("SELECT t FROM Ticket t WHERE t.estado IN :estados ORDER BY t.fecha ASC")
	 List<Ticket> findByEstadoInOrderByFechaAsc(@Param("estados") List<String> estados);
}
