package com.SGA.servicio;

import java.util.List;
import java.util.Optional;


import com.SGA.dto.StandarEstudianteDto;
import org.springframework.stereotype.Service;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Estudiante;
 

@Service
public interface EstudianteService{ 
	public List<Estudiante> all();
	public Iterable<Estudiante> findAll();//Colección en forma de iterable
	
	public Optional<Estudiante> findById(Long id); //Que nos encuentre la persona por medio del id
	
	
	public Estudiante guardar(StandarEstudianteDto estudiante);//actualizar entidad o guardarla, le pasamos una persona y va a guardar una persona
	
	public void deleteById(Long id); //Elimina un Estudiante por id, el metodo es void significa que no devuelve nada

	//public Estudiante ActualizarEstudiante(Long numeroDocumento,Estudiante unEstudiante);

	List<Estudiante> listarGenero(String est_genero, Long id_sede);
}