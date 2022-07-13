package com.SGA.controlador;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SGA.entidades.Institucion;
import com.SGA.entidades.Secretaria;
import com.SGA.servicio.InstitucionService;
 



@RestController
@RequestMapping("/api/institucion")

public class InstitucionController {
	
	@Autowired
	private InstitucionService institucionService;
	
	//Create
	@PostMapping
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<?> create (@Validated  @RequestBody  Institucion institucion){
		return ResponseEntity.status(HttpStatus.CREATED).body(institucionService.save(institucion));
	}
	
	//Read

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value="id")   Long estId){
		Optional<Institucion> oInstitucion = institucionService.findById(estId);
		if(!oInstitucion.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oInstitucion);
	}
	

	//Update 

	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/{id}") 
	public ResponseEntity<?> update(@RequestBody Institucion institucionDetails, @PathVariable(value="id") Long estId){
		Optional<Institucion> institucion = institucionService.findById(estId);
		if(!institucion.isPresent()) {
			
			return ResponseEntity.notFound().build();
		}

		institucion.get().setCodDane(institucionDetails.getCodDane());
		institucion.get().setNombre(institucionDetails.getNombre());
		institucion.get().getRector().setId(institucionDetails.getRector().getId());
		institucion.get().getDepartamento().setIdDepartamento(institucionDetails.getDepartamento().getIdDepartamento());
		institucion.get().setFechaModificacion(new Date().toString());
		//TERMINAR UPDATE DE INSTITUCION
		
		return ResponseEntity.status(HttpStatus.CREATED).body(institucionService.save(institucion.get()));
	
}	
	//Delete 

	@CrossOrigin(origins="http://localhost:4200")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long estId){
		if(!institucionService.findById(estId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		System.out.print("hola");
		institucionService.deleteById(estId);
		
		return ResponseEntity.ok().build();
	}

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping
	public List<Institucion>readAll(){
	
		List<Institucion> institucion = StreamSupport 
				.stream(institucionService.findAll().spliterator(), false)
				.collect(Collectors.toList()); 
				
		return institucion;
	
	}
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping({"id"})
	public List<Institucion>readAllaaa(){
	
		List<Institucion> institucion = StreamSupport 
				.stream(institucionService.findAll().spliterator(), false)
				.collect(Collectors.toList()); 
				
		return institucion;
	
	}
	

	@GetMapping(value ={"/municipio/{id_municipio}"})
	public List<Institucion> listarInstitucion(@PathVariable("id_municipio") Long id_municipio){
		return institucionService.listarInstitucion(id_municipio);
	}
}
