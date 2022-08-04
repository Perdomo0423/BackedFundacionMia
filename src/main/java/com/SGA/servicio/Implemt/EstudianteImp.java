package com.SGA.servicio.Implemt;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.SGA.dto.StandarEstudianteDto;
import com.SGA.servicio.EstudianteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SGA.entidades.Estudiante;
import com.SGA.repositorio.EstudianteRepository;

@Service
public  class EstudianteImp implements EstudianteService {
	@Autowired
	private EstudianteRepository estudianteRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Estudiante> findAll() {
		return estudianteRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true) //Una transaccion de solo lectura la cual no va a cambiar el estado de nuestra base de datos
	public Optional<Estudiante> findById(Long id) {
		return estudianteRepository.findById(id); //Usamos el metodo de la clase instanciada para poder buscar la estudiante por  el metodo de id
	}

	@Override
	@Transactional(readOnly = false)
	public Estudiante guardar(StandarEstudianteDto estudiante) {
		estudiante.setFechaCreacion(new Date());
		return estudianteRepository.save(mapper.map(estudiante, Estudiante.class));
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

	@Override
	public List<Estudiante> all() {
		return this.estudianteRepository.findAll();
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
	