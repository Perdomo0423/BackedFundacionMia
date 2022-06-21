package com.SGA.servicio.Implemt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGA.entidades.UsuarioRol;
import com.SGA.repositorio.UsuarioRolRepository;
import com.SGA.servicio.UsuarioRolService;

@Service
public class UsuarioRolImp implements UsuarioRolService{
	@Autowired
	private UsuarioRolRepository repository;
	
	@Override
	public List<UsuarioRol> all() {		
		return this.repository.findAll();
	}

//	@Override
//	public Optional<Usuario> findById(Long id) {		
//		return this.repository.findById(id);
//	}

}
