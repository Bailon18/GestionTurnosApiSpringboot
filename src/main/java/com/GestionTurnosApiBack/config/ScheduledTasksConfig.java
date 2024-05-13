package com.GestionTurnosApiBack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.GestionTurnosApiBack.model.services.AtencionService;

@Configuration
@EnableScheduling
public class ScheduledTasksConfig {
    /*@Autowired
    private AtencionService atencionService;

    @Scheduled(fixedRate = 6000)
    public void actualizarEstadoCompetenciasVencidas() {
    	atencionService.obtenerTicketsPendientesYEnAtencionOrdenadosPorFecha();
    }*/
}