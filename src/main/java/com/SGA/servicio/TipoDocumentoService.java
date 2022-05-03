package com.SGA.servicio;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.SGA.entidades.TipoDocumento;
 


@Service
public interface TipoDocumentoService {
	
	public Iterable<TipoDocumento> findAll(); //Buscar todos los tipos documentos
	
	public Optional<TipoDocumento> findById(Integer id); //Buscar  los tipos de documento por id
	
	public TipoDocumento save(TipoDocumento tipoDocumento); //Actualizar los tipo de documentos

}