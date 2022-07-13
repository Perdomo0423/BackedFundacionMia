package com.SGA.servicio.Implemt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Secretaria;
import com.SGA.entidades.Zona;
import com.SGA.repositorio.ContratistaRepository;
import com.SGA.servicio.ContratistaService;

@Service
public class ContratistaImp implements ContratistaService{
	@Autowired
	private ContratistaRepository repository;
	
	@Override
	public List<Contratista> all() {		
		return this.repository.findAll();
	}

	@Override
	public Optional<Contratista> findById(Long nit) {		
		return this.repository.findById(nit);
	}

	@Override
	public Contratista save(Contratista contratista) {
		return this.repository.save(contratista);
	}

	@Override
	public void delete(Long nit) {
		this.repository.deleteById(nit);		
	}


	@Override
	public List<Contratista> listarZona(Long id_Zona) {
		return repository.listZona(id_Zona);

	}
	
}
