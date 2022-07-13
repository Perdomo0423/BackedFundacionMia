package com.SGA.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SGA.entidades.Secretaria;


@Service
public interface SecretariaService {
	
	List<Secretaria> listarSecretaria(Long id_departamento);

}
