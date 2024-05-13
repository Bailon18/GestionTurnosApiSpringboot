package com.GestionTurnosApiBack.model.repository;

import java.util.List;

import com.GestionTurnosApiBack.model.entity.VideoSalaEspera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VideoSalaEsperaRepository extends JpaRepository<VideoSalaEspera, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE video_espera SET seleccionado = CASE WHEN id = :videoId THEN true ELSE false END", nativeQuery = true)
    void updateSeleccionadoEstado(Long videoId);

    VideoSalaEspera findBySeleccionado(boolean seleccionado);
}
