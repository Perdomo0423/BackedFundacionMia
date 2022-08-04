package com.SGA.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.SGA.dto.StandarEstudianteDto;
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

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Estudiante;
import com.SGA.servicio.EstudianteService;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {
	@Autowired
	private EstudianteService estudianteService;

	@PostMapping
	public ResponseEntity<?> create (@Validated  @RequestBody StandarEstudianteDto estudiante) {
		return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.guardar(estudiante));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value="id")   Long estId){
		Optional<Estudiante> oEstudiante = estudianteService.findById(estId);
		if(!oEstudiante.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oEstudiante);
	}
	
	/*
	@GetMapping("/doc/{doc}")
	public ResponseEntity<?> read(@PathVariable(value="doc")   String numeroDocumento){
		Optional<Estudiante> oEstudiante = estudianteService.findByUnaPersonaNumeroDocumento(numeroDocumento);
		if(!oEstudiante.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oEstudiante);
	}
	*/

	/*@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Estudiante estudianteDetails, @PathVariable(value="id") Long estId){
		Optional<Estudiante> estudiante = estudianteService.findById(estId);
		if(!estudiante.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		estudiante.get().setGrado(estudianteDetails.getGrado());
		estudiante.get().setDirector(estudianteDetails.getDirector());
		estudiante.get().setNombreAcudiente(estudianteDetails.getNombreAcudiente());
		estudiante.get().setParentescoAcudiente(estudianteDetails.getParentescoAcudiente());
		estudiante.get().getUnaPersona().getUnTipoDocumento().setIdTipoDocumento(estudianteDetails.getUnaPersona().getUnTipoDocumento().getIdTipoDocumento()); 
		estudiante.get().getUnaPersona().setNumeroDocumento(estudianteDetails.getUnaPersona().getNumeroDocumento()); 
		estudiante.get().getUnaPersona().setNombre(estudianteDetails.getUnaPersona().getNombre()); 
		estudiante.get().getUnaPersona().setApellido(estudianteDetails.getUnaPersona().getApellido()); 
		estudiante.get().getUnaPersona().setFechaNacimiento(estudianteDetails.getUnaPersona().getFechaNacimiento());
		estudiante.get().getUnaPersona().setTelefono(estudianteDetails.getUnaPersona().getTelefono());
		estudiante.get().getUnaPersona().setDireccion(estudianteDetails.getUnaPersona().getDireccion());
		estudiante.get().getUnaPersona().setBarrio(estudianteDetails.getUnaPersona().getBarrio()); 
		estudiante.get().getUnaPersona().setFechaModificacion(estudianteDetails.getUnaPersona().getFechaModificacion());
		estudiante.get().getUnaPersona().setEstado(estudianteDetails.getUnaPersona().getEstado());
		return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.save(estudiante.get()));
	}*/

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long estId){
		if(!estudianteService.findById(estId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		estudianteService.deleteById(estId);
		
		return ResponseEntity.ok().build();
	}

	 @GetMapping("/listar")
	    public List<Estudiante> all() {
	        return estudianteService.all();
	    }
	 
	@GetMapping(value ={"/genero/{id_sede}/{est_Genero}"})
	public List<Estudiante> listarGenero(@PathVariable("est_Genero") String est_genero, @PathVariable("id_sede") Long id_sede){
		return estudianteService.listarGenero(est_genero, id_sede);
	}
}
