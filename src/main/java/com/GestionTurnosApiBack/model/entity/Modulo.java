package com.GestionTurnosApiBack.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "modulos")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE") 
    private boolean activo;

    @ManyToMany
    @JoinTable(
        name = "servicios_modulos",
        joinColumns = @JoinColumn(name = "id_modulo"),
        inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<Servicio> servicios;
    
	public Modulo() {

	}

	public Modulo(Long id) {
		this.id = id;
	}

	public Modulo(Long id, String nombre, boolean activo, List<Servicio> servicios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
		this.servicios = servicios;
	}

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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Modulo [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", activo=");
		builder.append(activo);
		builder.append(", servicios=");
		builder.append(servicios);
		builder.append("]");
		return builder.toString();
	}

    
}
