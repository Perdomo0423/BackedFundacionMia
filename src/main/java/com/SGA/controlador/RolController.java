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

import com.SGA.entidades.Rol;
import com.SGA.servicio.RolService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/rol")

public class RolController {

	@Autowired 
	private RolService service;

	@GetMapping("listar")
	public List<Rol> all() {
		return service.all();
	}

	@GetMapping("listar/{id}")
	public Optional<Rol> show(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping("guardar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Rol save(@RequestBody Rol rol) {
		return service.save(rol);
	}
	
	
	@PutMapping("actualizar/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Rol update(@PathVariable Long id, @RequestBody Rol rol) {	
		Optional<Rol> op = service.findById(id);
		
		Rol rolsUpdate = new Rol();
		if (!op.isEmpty()) {			
			rolsUpdate = op.get();					
			rolsUpdate= rol;			
			rolsUpdate.setId(id);
			
		}
		return service.save(rolsUpdate);
				
	}
	
	@DeleteMapping("eliminar/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
}
