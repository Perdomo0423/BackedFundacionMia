package com.SGA.servicio.Implemt;
 
import java.util.List;
import java.util.Optional;

import com.SGA.servicio.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SGA.entidades.Institucion;
import com.SGA.repositorio.InstitucionRepository;
 
 
@Service
public class InstitucionImp implements InstitucionService {

	@Autowired
	private InstitucionRepository institucionRepository; 


	@Override
	@Transactional(readOnly = true)
	public Iterable<Institucion> findAll() {
		
		//No exponer la contraseña hacia el lado del cliente
		List<Institucion> institucion = institucionRepository.findAll();
		for(int i=0; i< institucion.size(); i++) {
			institucion.get(i).getRector().setPassword("");
		}
		return institucion;
	}
	
	@Override
	@Transactional(readOnly = true) 
	public Optional<Institucion> findById(Long id) {
		Optional<Institucion> institucion = institucionRepository.findById(id);
		institucion.get().getRector().setPassword("");
		return institucion; 
	}

	@Override
	@Transactional(readOnly = true) 
	public Institucion save(Institucion institucion) {
		
		return institucionRepository.save(institucion);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		institucionRepository.deleteById(id);
	}

	@Override
	public List<Institucion> listarInstitucion(Long id_municipio) {
		return  institucionRepository.listInstitucion(id_municipio);
	}
	


}