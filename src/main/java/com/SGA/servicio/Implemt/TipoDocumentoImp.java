package com.SGA.servicio.Implemt;

import java.util.Optional;


import com.SGA.servicio.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SGA.entidades.TipoDocumento;
import com.SGA.repositorio.TipoDocumentoRepository;
 

@Service
public class TipoDocumentoImp implements TipoDocumentoService {
	@Autowired
	public TipoDocumentoRepository tipoDocumentoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<TipoDocumento> findAll() {
		return tipoDocumentoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoDocumento> findById(Integer id) {
		
		return tipoDocumentoRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public TipoDocumento save(TipoDocumento tipodocumento) {
		
		return tipoDocumentoRepository.save(tipodocumento);
	}

}
