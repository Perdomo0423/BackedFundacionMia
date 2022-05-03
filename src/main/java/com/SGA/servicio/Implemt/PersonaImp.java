package com.SGA.servicio.Implemt;

import com.SGA.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGA.entidades.Persona;
import com.SGA.repositorio.PersonaRepository;

@Service
public class PersonaImp implements PersonaService {

	@Autowired
	PersonaRepository personaRepository;
	
	@Override
	public Persona save(Persona unaPersona) {
		//TODO Auto-generated method stub
		return personaRepository.save(unaPersona);
	}

}
