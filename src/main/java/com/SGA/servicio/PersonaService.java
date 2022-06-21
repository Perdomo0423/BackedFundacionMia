package com.SGA.servicio;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.SGA.entidades.Persona;

@Repository
public interface PersonaService {
	public List<Persona> all();
	Persona save(Persona unaPersona);
}
