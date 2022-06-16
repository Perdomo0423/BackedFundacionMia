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

import com.SGA.entidades.Contratista;
import com.SGA.servicio.ContratistaService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/contratista")

public class ContratistaController{

	@Autowired 
	private ContratistaService service;

	@GetMapping("listar")
	public List<Contratista> all() {
		return service.all();
	}

	@GetMapping("listar/{nit}")
	public Optional<Contratista> show(@PathVariable Long nit) {
		return service.findById(nit);
	}

	@PostMapping("guardar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Contratista save(@RequestBody Contratista contratista) {
		return service.save(contratista);
	}
	
	
	@PutMapping("actualizar/{nit}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Contratista update(@PathVariable Long nit, @RequestBody Contratista contratista) {	
		Optional<Contratista> op = service.findById(nit);
		
		Contratista ContratistasUpdate = new Contratista();
		if (!op.isEmpty()) {			
			ContratistasUpdate = op.get();					
			ContratistasUpdate= contratista;			
			ContratistasUpdate.setNit(nit);
			
		}
		return service.save(ContratistasUpdate);
				
	}
	
	@DeleteMapping("eliminar/{nit}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long nit) {
		service.delete(nit);
	}
	
}
