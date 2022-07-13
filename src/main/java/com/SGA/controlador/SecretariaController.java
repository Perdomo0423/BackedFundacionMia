package com.SGA.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Estudiante;
import com.SGA.entidades.Secretaria;
import com.SGA.servicio.SecretariaService;

@RestController
@RequestMapping("/api/secretaria")
public class SecretariaController {
	
	@Autowired
	private SecretariaService secreSevice;
	
	
	@GetMapping("listar")
	public List<Secretaria> all() {
		return secreSevice.all();
	}
	
	

	@GetMapping(value ={"/departamento/{id_departamento}"})
	public List<Secretaria> listarSecretaria(@PathVariable("id_departamento") Long id_departamento){
		return secreSevice.listarSecretaria(id_departamento);
	}
}
