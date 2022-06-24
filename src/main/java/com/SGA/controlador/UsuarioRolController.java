package com.SGA.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SGA.entidades.UsuarioRol;
import com.SGA.servicio.UsuarioRolService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/usuariorol")

public class UsuarioRolController{

	@Autowired 
	private UsuarioRolService service;

	@GetMapping("listar")
	public List<UsuarioRol> all() {
		return service.all();
	}

	
}
