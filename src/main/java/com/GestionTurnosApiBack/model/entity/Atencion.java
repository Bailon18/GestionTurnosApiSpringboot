package com.GestionTurnosApiBack.model.entity;


import jakarta.persistence.*;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;

import com.GestionTurnosApiBack.security.Entity.User;

@Entity
@Table(name = "atenciones")
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;
    

    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'Pendiente'")
    private String estado;

    @CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
    private Timestamp  fechaHoraInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp  fechaHoraFin;

    public Atencion() {
    }

    public Atencion(Ticket ticket, User usuario, Modulo modulo, String estado, Timestamp fechaHoraInicio, Timestamp fechaHoraFin ) {
        this.ticket = ticket;
        this.usuario = usuario;
        this.modulo = modulo; 
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Timestamp getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Timestamp fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
    
   
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Atencion [id=");
		builder.append(id);
		builder.append(", ticket=");
		builder.append(ticket);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", modulo=");
		builder.append(modulo);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", fechaHoraInicio=");
		builder.append(fechaHoraInicio);
		builder.append(", fechaHoraFin=");
		builder.append(fechaHoraFin);
		builder.append("]");
		return builder.toString();
	}


    

}
