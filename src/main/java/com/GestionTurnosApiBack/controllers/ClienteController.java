package com.GestionTurnosApiBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GestionTurnosApiBack.model.entity.Cliente;
import com.GestionTurnosApiBack.model.services.ClienteService;
import com.GestionTurnosApiBack.model.services.ReniecService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = { "*" })
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    
    @Autowired
    private ReniecService reniecService;

    @GetMapping("/dni/{numero}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable int numero) throws JsonMappingException, JsonProcessingException {
        Cliente cliente = reniecService.consultarApiReniec(numero);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }
    
    @GetMapping("/buscarPorDni/{dni}")
    public ResponseEntity<Cliente> buscarClientePorDni(@PathVariable String dni) {
        Cliente cliente = clienteService.buscarClientePorDni(dni);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
