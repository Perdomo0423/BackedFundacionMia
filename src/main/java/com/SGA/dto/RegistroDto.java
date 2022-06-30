package com.SGA.dto;

import java.util.Date;

import com.SGA.entidades.Departamento;
import com.SGA.entidades.Municipio;
import com.SGA.entidades.Persona;
import com.SGA.entidades.Rol;
import com.SGA.entidades.TipoDocumento;

public class RegistroDto {
	private String username;
	private String email;
	private Persona unaPersona;
	private TipoDocumento unTipoDocumento;
	 private Departamento unDepartamento;
	 private Municipio unMunicipio;
	 private Rol unRol;
	 private Date fechaNacimiento;
	

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Municipio getUnMunicipio() {
		return unMunicipio;
	}
	public void setUnMunicipio(Municipio unMunicipio) {
		this.unMunicipio = unMunicipio;
	}

	
	public TipoDocumento getUnTipoDocumento() {
		return unTipoDocumento;
	}
	public Departamento getUnDepartamento() {
		return unDepartamento;
	}
	public void setUnDepartamento(Departamento unDepartamento) {
		this.unDepartamento = unDepartamento;
	}
	public void setUnTipoDocumento(TipoDocumento unTipoDocumento) {
		this.unTipoDocumento = unTipoDocumento;
	}
	
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
