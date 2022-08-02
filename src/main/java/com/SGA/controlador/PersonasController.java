package com.SGA.controlador;



import com.SGA.entidades.Persona;

import com.SGA.servicio.PersonaService1;
import com.SGA.servicio.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/persona")
public class PersonasController {

    @Autowired
    private PersonaService1 personaservice1;
    
    @Autowired
    private PersonaService personaservice;
    
    @Autowired
    private UsuarioService usuarioservice;
    
	@GetMapping("listar")
	public List<Persona> all() {
		return personaservice.all();
	}
	
	

    @GetMapping("/listar/{id}")
    public ResponseEntity<Persona> obtenerPersona (@PathVariable Long id){
        try{
            Persona persona = personaservice1.obtenerPersonaPorId(id);
            return new ResponseEntity<Persona>(persona, HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/Actualizar/{id}")
    public ResponseEntity<?> actualizarPersona(@RequestBody Persona persona, @PathVariable Long id){
         try{
             Persona personaExistente = personaservice1.obtenerPersonaPorId(id);
             personaExistente.setEstado(persona.getEstado());
             personaExistente.setFechaModificacion(new Date().toString());
             personaservice1.guardarPersona(personaExistente);
             return new ResponseEntity<Persona>(HttpStatus.OK);

             
         }catch (Exception e){
             return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);

         }
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPersona(@PathVariable Long id){
        personaservice1.eliminarPersona(id);
    }

}
