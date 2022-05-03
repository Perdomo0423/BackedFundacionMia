package com.SGA.dto;

import java.util.Date;

public class ErrorDetalles {
	private Date  marcarDeTiempo;
	private String mensaje;
	private String detalles;
	public Date getMarcarDeTiempo() {
		return marcarDeTiempo;
	}
	public void setMarcarDeTiempo(Date marcarDeTiempo) {
		this.marcarDeTiempo = marcarDeTiempo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public ErrorDetalles(Date marcarDeTiempo, String mensaje, String detalles) {
		super();
		this.marcarDeTiempo = marcarDeTiempo;
		this.mensaje = mensaje;
		this.detalles = detalles;
	}
	
}
