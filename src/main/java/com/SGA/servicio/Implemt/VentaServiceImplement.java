package com.SGA.servicio.Implemt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGA.entidades.*;
import com.SGA.repositorio.*;
import com.SGA.servicio.*;

@Service
public class VentaServiceImplement implements IVentaService{
	
	@Autowired
	private IVentaRepository repository;
	
	@Override
	public List<Venta> all() {		
		return this.repository.findAll();
	}

	@Override
	public Optional<Venta> findById(Long id) {		
		return this.repository.findById(id);
	}

	@Override
	public Venta save(Venta venta) {
		return this.repository.save(venta);
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);		
	}		
}
