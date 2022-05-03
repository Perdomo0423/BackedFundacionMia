package com.SGA.servicio;

import org.springframework.stereotype.Repository;

import com.SGA.entidades.Persona;

@Repository
public interface PersonaService {
	Persona save(Persona unaPersona);
}
