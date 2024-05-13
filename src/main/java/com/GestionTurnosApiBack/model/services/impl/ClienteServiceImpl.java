package com.GestionTurnosApiBack.model.services.impl;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.GestionTurnosApiBack.model.entity.Cliente;
import com.GestionTurnosApiBack.model.repository.ClienteRepository;
import com.GestionTurnosApiBack.model.services.ClienteService;
import com.GestionTurnosApiBack.model.services.ReniecService;
import com.fasterxml.jackson.core.JsonProcessingException;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ReniecService reniecService;

    @Override
    public Cliente buscarCliente(int numero) {
        Cliente clienteReniec = null;
		try {
			clienteReniec = reniecService.consultarApiReniec(numero);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return clienteReniec;
    }


    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarClientePorDni(String dni) {
        return clienteRepository.findByNumeroDocumento(dni).orElse(null);
    }

   


}
