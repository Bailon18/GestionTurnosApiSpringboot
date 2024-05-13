package com.GestionTurnosApiBack.controllers;

import com.GestionTurnosApiBack.model.entity.Servicio;
import com.GestionTurnosApiBack.model.entity.VideoSalaEspera;
import com.GestionTurnosApiBack.model.services.VideoSalaEsperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/videos")
@CrossOrigin(origins = { "*" })
public class VideoSalaEsperaController {

    @Autowired
    private VideoSalaEsperaService videoSalaEsperaService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<VideoSalaEspera> guardar(
            @RequestPart("video") VideoSalaEspera videoSalaEspera,
            @RequestPart(name = "videoarchivo", required = false) MultipartFile videoarchivo
    ) throws IOException {

        if (videoarchivo != null && !videoarchivo.isEmpty()) {
            byte[] videoBytes = videoarchivo.getBytes();
            videoSalaEspera.setVideo(videoBytes);
        }

        VideoSalaEspera nuevovideo = videoSalaEsperaService.guardar(videoSalaEspera);
        return new ResponseEntity<>(nuevovideo, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<VideoSalaEspera> actualizar(
            @PathVariable Long id,
            @RequestPart("video") VideoSalaEspera videoSalaEspera,
            @RequestPart(name = "videoarchivo", required = false) MultipartFile videoarchivo
    ) throws IOException {

        System.out.println("archivo "+ videoarchivo);

        if (videoarchivo != null && !videoarchivo.isEmpty()) {
            byte[] videoBytes = videoarchivo.getBytes();
            videoSalaEspera.setVideo(videoBytes);
        }

        VideoSalaEspera nuevovideo = videoSalaEsperaService.actualizarVideo(id, videoSalaEspera);
        if (nuevovideo != null) {
            return new ResponseEntity<>(nuevovideo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<VideoSalaEspera>> listarVideos() {
        List<VideoSalaEspera> videos = videoSalaEsperaService.listarVideos();
        return ResponseEntity.ok(videos);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVideo(@PathVariable Long id) {
        videoSalaEsperaService.eliminarVideo(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoSalaEspera> buscarVideoPorId(@PathVariable Long id) {
        VideoSalaEspera video = videoSalaEsperaService.buscarVideoPorId(id);
        return ResponseEntity.ok(video);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarVideo(@PathVariable Long id, @RequestBody VideoSalaEspera videoActualizado) {
        videoSalaEsperaService.actualizarVideo(id, videoActualizado);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/seleccionar/{videoId}")
    public ResponseEntity<Object> seleccionarVideo(@PathVariable Long videoId) {
        try {
            videoSalaEsperaService.actualizarEstadoSeleccionado(videoId);
            return ResponseEntity.ok().body("{\"message\": \"El video se ha seleccionado correctamente.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Ha ocurrido un error al seleccionar el video: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/seleccionado")
    public ResponseEntity<VideoSalaEspera> buscarVideoSeleccionado() {
        VideoSalaEspera video = videoSalaEsperaService.buscarPorSeleccionado(true);
        return ResponseEntity.ok(video);
    }

}
