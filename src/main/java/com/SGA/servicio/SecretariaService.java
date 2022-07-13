package com.SGA.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Secretaria;


@Service
public interface SecretariaService {
	
	public List<Secretaria> all();
	
	List<Secretaria> listarSecretaria(Long id_departamento);

}
