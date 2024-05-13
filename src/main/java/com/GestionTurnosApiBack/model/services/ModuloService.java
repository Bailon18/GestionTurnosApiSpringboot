package com.GestionTurnosApiBack.model.services;

import java.util.List;

import com.GestionTurnosApiBack.Utils.ModuloTicketDTO;
import com.GestionTurnosApiBack.model.entity.*;




public interface ModuloService {
    
    Modulo buscarPorId(Long id);
    
    List<Modulo> listarTodos();
    
    Modulo guardar(Modulo modulo);
    
    Modulo actualizar(Long id, Modulo modulo);
    
    void cambiarEstadoModulo(Long id, boolean activo);
    
    void agregarServiciosAModulo2(Long idModulo, List<Servicio> servicios);
    
    Modulo actualizarModulo(Long idModulo, Modulo moduloActualizado);
    
    List<ModuloTicketDTO> getTotalTicketsByServicioo(Long idServicio);
    
    
}
