package com.GestionTurnosApiBack.model.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GestionTurnosApiBack.Utils.ModuloTicketDTO;
import com.GestionTurnosApiBack.model.entity.Modulo;
import com.GestionTurnosApiBack.model.entity.Servicio;
import com.GestionTurnosApiBack.model.repository.ModuloRepository;
import com.GestionTurnosApiBack.model.services.ModuloService;

@Service
public class ModuloServiceImpl implements ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    @Transactional(readOnly = true)
    public Modulo buscarPorId(Long id) {
        Optional<Modulo> optionalModulo = moduloRepository.findById(id);
        return optionalModulo.orElse(null);
    }

    @Override
    @Transactional
    public Modulo guardar(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    @Override
    @Transactional
    public Modulo actualizar(Long id, Modulo modulo) {
        if (moduloRepository.existsById(id)) {
            modulo.setId(id);
            return moduloRepository.save(modulo);
        }
        return null;
    }

    @Override
    @Transactional
    public void cambiarEstadoModulo(Long id, boolean activo) {
        Optional<Modulo> optionalModulo = moduloRepository.findById(id);
        optionalModulo.ifPresent(modulo -> {
            modulo.setActivo(activo);
            moduloRepository.save(modulo);
        });
    }

    @Override
    public List<Modulo> listarTodos() {
        return moduloRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    
    @Override
    public void agregarServiciosAModulo2(Long idModulo, List<Servicio> servicios) {
        Modulo modulo = moduloRepository.findById(idModulo).orElse(null);
        if (modulo != null) {
            modulo.getServicios().addAll(servicios);
            moduloRepository.save(modulo);
        }
    }
    
    @Override
    public Modulo actualizarModulo(Long idModulo, Modulo moduloActualizado) {
        Modulo moduloExistente = moduloRepository.findById(idModulo).orElse(null);
        if (moduloExistente != null) {
          
            moduloExistente.setNombre(moduloActualizado.getNombre());
            moduloExistente.setActivo(moduloActualizado.isActivo());
            moduloExistente.setServicios(moduloActualizado.getServicios());
           
            return moduloRepository.save(moduloExistente);
        }
        return null; 
    }

  
    public List<ModuloTicketDTO> getTotalTicketsByServicioo(Long idServicio) {
        
    	List<Object[]> results = moduloRepository.getTotalTicketsByModuloId(idServicio);
        
        List<ModuloTicketDTO> moduloTickets = new ArrayList<>();

        for (Object[] result : results) {
            System.out.println("Valor de result[0]: " + result[0]);
            System.out.println("Valor de result[1]: " + result[1]);

            ModuloTicketDTO moduloTicketDTO = new ModuloTicketDTO((long) result[0], ((BigDecimal) result[1]).intValue());
            moduloTickets.add(moduloTicketDTO);
        }

        return moduloTickets;
    }

}
