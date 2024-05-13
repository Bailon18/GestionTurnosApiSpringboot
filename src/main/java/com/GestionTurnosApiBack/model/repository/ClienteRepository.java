package com.GestionTurnosApiBack.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GestionTurnosApiBack.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Optional<Cliente> findByNumeroDocumento(String numerodocumento);

}
