package com.SGA.servicio.Implemt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGA.entidades.Zona;
import com.SGA.repositorio.ZonaRepository;
import com.SGA.servicio.ZonaService;

@Service
public class ZonaImp implements ZonaService{
	@Autowired
	private ZonaRepository repository;
	
	@Override
	public List<Zona> all() {		
		return this.repository.findAll();
	}

	@Override
	public Optional<Zona> findById(Long id) {		
		return this.repository.findById(id);
	}

	@Override
	public Zona save(Zona zona) {
		return this.repository.save(zona);
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);		
	}

	@Override
	public List<Zona> listarSecretaria(Long id_secretaria) {
		return repository.listZonaSecretaria(id_secretaria);
	
	}	
}
