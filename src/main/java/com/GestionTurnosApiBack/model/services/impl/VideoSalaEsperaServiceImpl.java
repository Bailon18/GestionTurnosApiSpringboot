package com.GestionTurnosApiBack.model.services.impl;

import com.GestionTurnosApiBack.model.entity.VideoSalaEspera;
import com.GestionTurnosApiBack.model.repository.VideoSalaEsperaRepository;
import com.GestionTurnosApiBack.model.services.VideoSalaEsperaService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoSalaEsperaServiceImpl implements VideoSalaEsperaService {

    @Autowired
    private VideoSalaEsperaRepository videoSalaEsperaRepository;

    @Override
    public List<VideoSalaEspera> listarVideos() {
        return videoSalaEsperaRepository.findAll();
    }

    @Override
    public void seleccionarVideo(Long id, boolean estadoSeleccion) {
        VideoSalaEspera video = videoSalaEsperaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video no encontrado con ID: " + id));
        video.setSeleccionado(estadoSeleccion);
        videoSalaEsperaRepository.save(video);
    }

    @Override
    public void eliminarVideo(Long id) {
        videoSalaEsperaRepository.deleteById(id);
    }

    @Override
    public VideoSalaEspera buscarVideoPorId(Long id) {
        return videoSalaEsperaRepository.findById(id).orElse(null);
    }


    @Override
    public VideoSalaEspera guardar(VideoSalaEspera video) {
        return videoSalaEsperaRepository.save(video);
    }

    @Override
    public void actualizarEstadoSeleccionado(Long videoId) {
        videoSalaEsperaRepository.updateSeleccionadoEstado(videoId);
    }

    @Override
    public VideoSalaEspera buscarPorSeleccionado(boolean seleccionado) {
        return videoSalaEsperaRepository.findBySeleccionado(seleccionado);
    }

    @Override
    public VideoSalaEspera actualizarVideo(Long id, VideoSalaEspera videoActualizado) {

        VideoSalaEspera videoSalaEspera = videoSalaEsperaRepository.findById(id).orElse(null);

        if (videoSalaEspera != null) {

            if(videoActualizado.getVideo() == null){
                videoActualizado.setVideo(videoSalaEspera.getVideo());
            }
            videoActualizado.setId(id);
            return videoSalaEsperaRepository.save(videoActualizado);
        }
        return null;
    }

}
