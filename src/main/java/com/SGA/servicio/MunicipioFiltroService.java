package com.SGA.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SGA.entidades.Municipio;


@Service
public interface MunicipioFiltroService {
	
	List<Municipio> listarMunici(Long id_zona);

}
