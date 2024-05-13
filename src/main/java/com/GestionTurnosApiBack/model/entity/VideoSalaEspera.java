package com.GestionTurnosApiBack.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "video_espera")
public class VideoSalaEspera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private boolean seleccionado;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] video;

    public VideoSalaEspera() {
    }

    public VideoSalaEspera(String nombre, boolean seleccionado, byte[] video) {
        this.nombre = nombre;
        this.seleccionado = seleccionado;
        this.video = video;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

}
