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

import com.SGA.entidades.OtroSi;
import com.SGA.entidades.Secretaria;
import com.SGA.servicio.OtroSiService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/otroSi")

public class OtroSiController{

	@Autowired 
	private OtroSiService service;

	@GetMapping("listar")
	public List<OtroSi> all() {
		return service.all();
	}

	@GetMapping("listar/{id}")
	public Optional<OtroSi> show(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping("guardar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OtroSi save(@RequestBody OtroSi otro) {
		return service.save(otro);
	}
	
	
	@PutMapping("actualizar/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OtroSi update(@PathVariable Long id, @RequestBody OtroSi otro) {	
		Optional<OtroSi> op = service.findById(id);
		
		OtroSi OtroSiUpdate = new OtroSi();
		if (!op.isEmpty()) {			
			OtroSiUpdate = op.get();					
			OtroSiUpdate= otro;			
			OtroSiUpdate.setId(id);
			
		}
		return service.save(OtroSiUpdate);
				
	}
	
	@DeleteMapping("eliminar/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@GetMapping(value ={"/contratista/{id_contratista}"})
	public List<OtroSi> listarOtroSi(@PathVariable("id_contratista") Long id_contratista){
		return service.listarOtroSi(id_contratista);
	}
}
