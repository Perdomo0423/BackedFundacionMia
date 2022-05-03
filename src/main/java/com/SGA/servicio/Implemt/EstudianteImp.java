package com.SGA.servicio.Implemt;


import java.util.List;
import java.util.Optional;

import com.SGA.servicio.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SGA.entidades.Estudiante;
import com.SGA.repositorio.EstudianteRepository;
 

@Service
public  class EstudianteImp implements EstudianteService {

	@Autowired
	private EstudianteRepository estudianteRepository; //Intanciamos la clase para poder usar todos los metodos que estan la clase repositorio


	@Override
	@Transactional(readOnly = true) //Una transaccion de solo lectura la cual no va a cambiar el estado de nuestra base de datos
	public Iterable<Estudiante> findAll() {
		
		return estudianteRepository.findAll(); //Usamos el metodo de la clase instanciada para poder listar todos los usuarios 
	}
	
	@Override
	@Transactional(readOnly = true) //Una transaccion de solo lectura la cual no va a cambiar el estado de nuestra base de datos
	public Optional<Estudiante> findById(Long id) {
		return estudianteRepository.findById(id); //Usamos el metodo de la clase instanciada para poder buscar la estudiante por  el metodo de id
	}

	@Override
	@Transactional(readOnly = false) //Una transaccion de  la cual va a cambiar el estado de nuestra base de datos va a crear o actualizar nuestra base de datos
	public Estudiante save(Estudiante estudiante) {
		return estudianteRepository.save(estudiante);
	}

	@Override
	@Transactional(readOnly = false) //Una transaccion de  la cual va a cambiar el estado de nuestra base de datos va a eliminar un dato de nuestra base de datos
	public void deleteById(Long id) {
		estudianteRepository.deleteById(id);
	}

	@Override
	public List<Estudiante> listarGenero(String est_genero, Long id_sede) {
		return estudianteRepository.listEstudianteGenero(est_genero, id_sede);
	}



	/*@Override
	public Estudiante ActualizarEstudiante(Long numeroDocumento, Estudiante unEstudiante) {
		boolean existeEst =  estudianteRepository.existsByNumeroDocumento((long) numeroDocumento);
		if(existeEst==true) {
			Estudiante estudiateActualizar = estudianteRepository.findByNumeroDocumento((long )numeroDocumento);
			estudiateActualizar.set(unEstudiante.)
		}
		return null;
	}*/
	




}
	