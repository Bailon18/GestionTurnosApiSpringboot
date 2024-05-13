package com.GestionTurnosApiBack.model.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroTicket;
    
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fecha; 

    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'Pendiente'")
    private String estado; // Pendiente,  En progreso, Cancelado
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;
    
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

    public Ticket() {
    }


    public Ticket(Long id, String numeroTicket, String estado, Cliente cliente, Servicio servicio, Modulo modulo) {
        this.id = id;
        this.numeroTicket = numeroTicket;
        this.estado = estado;
        this.cliente = cliente;
        this.servicio = servicio;
        this.modulo = modulo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(String numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
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
		builder.append("Ticket [id=");
		builder.append(id);
		builder.append(", numeroTicket=");
		builder.append(numeroTicket);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", cliente=");
		builder.append(cliente);
		builder.append(", servicio=");
		builder.append(servicio);
		builder.append("]");
		return builder.toString();
	}

    

}
