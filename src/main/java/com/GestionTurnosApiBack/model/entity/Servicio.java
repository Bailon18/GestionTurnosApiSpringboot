package com.GestionTurnosApiBack.model.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreServicio;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagen;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE") 
    private boolean activo;
    
    @ManyToMany(mappedBy = "servicios")
    private List<Modulo> modulos;

	public Servicio() {
		super();
	}

	public Servicio(Long id, String nombreServicio, byte[] imagen, boolean activo) {
		super();
		this.id = id;
		this.nombreServicio = nombreServicio;
		this.imagen = imagen;
		this.activo = activo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Servicio [id=");
		builder.append(id);
		builder.append(", nombreServicio=");
		builder.append(nombreServicio);
		builder.append(", activo=");
		builder.append(activo);
		builder.append("]");
		return builder.toString();
	}
    
	
  
}
