package com.SGA.servicio.Implemt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGA.entidades.Rol;
import com.SGA.repositorio.RolRepository;
import com.SGA.servicio.RolService;

@Service
public class RolServiceImp implements RolService{
	@Autowired
	private RolRepository repository;
	
	@Override
	public List<Rol> all() {		
		return this.repository.findAll();
	}

	@Override
	public Optional<Rol> findById(Long id) {		
		return this.repository.findById(id);
	}

	@Override
	public Rol save(Rol rol) {
		return this.repository.save(rol);
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);		
	}	
}
