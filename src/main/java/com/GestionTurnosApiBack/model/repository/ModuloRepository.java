package com.GestionTurnosApiBack.model.repository;

import com.GestionTurnosApiBack.model.entity.Modulo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.*;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {

	    @Transactional
	    @Modifying
	    @Query(value = "INSERT INTO servicios_modulos (id_modulo, id_servicio) VALUES (:idModulo, :idServicio)", nativeQuery = true)
	    void agregarServicioAModulo(Long idModulo, Long idServicio);
	    
	    
	    @Query(value = "SELECT " +
	            "    m.id AS idModulo, " +
	            "    SUM(CASE WHEN t.estado = 'Atendiendo' THEN 1 ELSE 0 END) + " +
	            "    SUM(CASE WHEN t.estado = 'Pendiente' THEN 1 ELSE 0 END) AS totalTickets " +
	            "FROM " +
	            "    modulos m " +
	            "LEFT JOIN " +
	            "    tickets t ON m.id = t.id_modulo " +
	            "LEFT JOIN " +
	            "    servicios_modulos sm ON m.id = sm.id_modulo " +
	            "WHERE " +
	            "    sm.id_servicio = :idServicio " +
	            "GROUP BY " +
	            "    m.id", nativeQuery = true)
	    List<Object[]> getTotalTicketsByModuloId(@Param("idServicio") Long idServicio);

}
