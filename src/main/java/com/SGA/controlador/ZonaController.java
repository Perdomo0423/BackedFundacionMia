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

import com.SGA.entidades.Estudiante;
import com.SGA.entidades.Zona;
import com.SGA.servicio.ZonaService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/zona")

public class ZonaController{

	@Autowired 
	private ZonaService service;

	@GetMapping("listar")
	public List<Zona> all() {
		return service.all();
	}

	@GetMapping("listar/{id}")
	public Optional<Zona> show(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping("guardar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Zona save(@RequestBody Zona zona) {
		return service.save(zona);
	}
	
	
	@PutMapping("actualizar/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Zona update(@PathVariable Long id, @RequestBody Zona zona) {	
		Optional<Zona> op = service.findById(id);
		
		Zona ZonasUpdate = new Zona();
		if (!op.isEmpty()) {			
			ZonasUpdate = op.get();					
			ZonasUpdate= zona;			
			ZonasUpdate.setId(id);
			
		}
		return service.save(ZonasUpdate);
				
	}
	
	@DeleteMapping("eliminar/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	@GetMapping(value ={"/secretaria/{id_secretaria}"})
	public List<Zona> listarSecretaria(@PathVariable("id_secretaria") Long id_secretaria){
		return service.listarSecretaria(id_secretaria);
	}
	
}
