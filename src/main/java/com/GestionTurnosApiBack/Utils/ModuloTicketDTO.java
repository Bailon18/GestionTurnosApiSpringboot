package com.GestionTurnosApiBack.Utils;

public class ModuloTicketDTO {
	
    private long idModulo;
    private int totalTickets;
    

    public ModuloTicketDTO() {
		
	}

	public ModuloTicketDTO(long idModulo, int totalTickets) {
        this.idModulo = idModulo;
        this.totalTickets = totalTickets;
    }

    public long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(long idModulo) {
        this.idModulo = idModulo;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
}
