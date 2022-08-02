package com.SGA.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.SGA.entidades.Estudiante;

public interface PdfRepository extends CrudRepository<Estudiante, Long>{
//
//	@Query(value ="select est_municipio_recidencia,id_sede,id_institucion,id_jornada,id_etnia, est_nombre1, est_apellido1, est_genero, zona from vista_estudiante", nativeQuery = true)
//	public List<Estudiante> findNamedeleteMailDireccion();
}
