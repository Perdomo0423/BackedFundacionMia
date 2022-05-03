package com.SGA.controlador;


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

import com.SGA.entidades.TipoDocumento;
import com.SGA.servicio.TipoDocumentoService;
 


@RestController
@RequestMapping("/api/tipoDocumento")

public class TipoDocumentoController {
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	
	//Create
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping
	public ResponseEntity<?> create (@Validated  @RequestBody  TipoDocumento tipoDocumento){
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoDocumentoService.save(tipoDocumento));
	}
	
	//Read
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value="id")   Integer estId){
		Optional<TipoDocumento> oTipoDocumento = tipoDocumentoService.findById(estId);
		if(!oTipoDocumento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oTipoDocumento);
	}
	//Update 
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/{id}") 
	public ResponseEntity<?> update(@RequestBody TipoDocumento tipodocumentoDetails, @PathVariable(value="id") Integer estId){
		Optional<TipoDocumento> tipoDocumento = tipoDocumentoService.findById(estId);
		if(!tipoDocumento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		tipoDocumento.get().setNombre(tipodocumentoDetails.getNombre()); 
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoDocumentoService.save(tipoDocumento.get()));
	
}	


	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping
	public List<TipoDocumento>readAll(){
	
		List<TipoDocumento> tipoDocumento = StreamSupport 
				.stream(tipoDocumentoService.findAll().spliterator(), false)
				.collect(Collectors.toList()); 
				
		return tipoDocumento;
	
	}
}
