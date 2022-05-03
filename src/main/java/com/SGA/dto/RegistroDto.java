package com.SGA.dto;

import com.SGA.entidades.Persona;
import com.SGA.entidades.Rol;

public class RegistroDto {
	private String username;
	private String email;
	private Persona unaPersona;
	private Rol unRol;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Persona getUnaPersona() {
		return unaPersona;
	}
	public void setUnaPersona(Persona unaPersona) {
		this.unaPersona = unaPersona;
	}
	public Rol getUnRol() {
		return unRol;
	}
	public void setUnRol(Rol unRol) {
		this.unRol = unRol;
	}
	

}
