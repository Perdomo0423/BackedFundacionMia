package com.SGA.servicio.Implemt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGA.entidades.Municipio;

import com.SGA.repositorio.MunicipioRepository;

import com.SGA.servicio.MunicipioFiltroService;


@Service
public class MunicipioFiltroImp implements MunicipioFiltroService{
	
	@Autowired
	private MunicipioRepository munyRepository;


	@Override
	public List<Municipio> listarMunici(Long id_zona) {
		return munyRepository.listMunicipio(id_zona);
	}

}
