package com.SGA.servicio.Implemt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.SGA.entidades.OtroSi;
import com.SGA.repositorio.OtroSiRepository;
import com.SGA.servicio.OtroSiService;

@Service
public class OtroSiImp implements OtroSiService{
	@Autowired
	private OtroSiRepository repository;
	
	@Override
	public List<OtroSi> all() {		
		return this.repository.findAll();
	}

	@Override
	public Optional<OtroSi> findById(Long id) {		
		return this.repository.findById(id);
	}

	@Override
	public OtroSi save(OtroSi otro) {
		return this.repository.save(otro);
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);		
	}

	@Override
	public List<OtroSi> listarOtroSi(Long id_contratista) {
		return repository.listOtroSi(id_contratista);
	}	
}
