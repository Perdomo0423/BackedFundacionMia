package com.SGA.servicio.Implemt;

import com.SGA.servicio.PersonaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Persona;
import com.SGA.repositorio.ContratistaRepository;
import com.SGA.repositorio.PersonaRepository;

@Service
public class PersonaImp implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	

	@Override
	public List<Persona> all() {		
		return this.personaRepository.findAll();
	}
	
	@Override
	public Persona save(Persona unaPersona) {
		//TODO Auto-generated method stub
		return personaRepository.save(unaPersona);
	}

}
