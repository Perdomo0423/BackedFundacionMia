package com.SGA.servicio;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Persona;
import com.SGA.entidades.Rol;
import com.SGA.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService1 {

    @Autowired
    private PersonaRepository personarepository;
    

    public List<Persona> listarPersona(){
        return personarepository.findAll();
    }

    public Persona obtenerPersonaPorId(Long id){
        return personarepository.findById(id).get();
    }


    public void guardarPersona(Persona persona){
        personarepository.save(persona);
    }

    public void eliminarPersona(Long id){
        personarepository.deleteById(id);
    }
}
//
//@Service
//public interface PersonaService2 {
//	
//	public List<Contratista> all();
//}
