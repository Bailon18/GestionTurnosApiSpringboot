package com.GestionTurnosApiBack.model.services;

import com.GestionTurnosApiBack.model.entity.Servicio;
import com.GestionTurnosApiBack.model.entity.VideoSalaEspera;
import com.GestionTurnosApiBack.model.repository.VideoSalaEsperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

public interface VideoSalaEsperaService {

    List<VideoSalaEspera> listarVideos() ;

    void seleccionarVideo(Long id, boolean estadoSeleccion) ;

    void eliminarVideo(Long id);

    VideoSalaEspera actualizarVideo(Long id, VideoSalaEspera videoActualizado);

    VideoSalaEspera buscarVideoPorId(Long id);



    VideoSalaEspera guardar(VideoSalaEspera video);
    void actualizarEstadoSeleccionado(Long videoId);

    VideoSalaEspera buscarPorSeleccionado(boolean seleccionado);
}
