package com.GestionTurnosApiBack.model.services;

import java.util.List;

import com.GestionTurnosApiBack.model.entity.Servicio;

public interface ServicioService {
    
    Servicio buscarPorId(Long id);
    
    List<Servicio> listarTodos();
    
    Servicio guardar(Servicio servicio);
    
    Servicio actualizar(Long id, Servicio servicio);
    
    void deshabilitarServicio(Long id, boolean activo);
    
 
}