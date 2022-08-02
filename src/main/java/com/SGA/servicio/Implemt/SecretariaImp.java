package com.SGA.servicio.Implemt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Secretaria;
import com.SGA.repositorio.SecretariaRepository;
import com.SGA.servicio.SecretariaService;

@Service
public class SecretariaImp implements SecretariaService{
	
	@Autowired
	private SecretariaRepository secreRepository;
	
	
	
	@Override
	public List<Secretaria> all() {		
		return this.secreRepository.findAll();
	}

	@Override
	public List<Secretaria> listarSecretaria(Long id_departamento) {
		return secreRepository.listSecretaria(id_departamento);

	}

}
