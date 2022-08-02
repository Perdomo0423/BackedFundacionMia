package com.SGA.repositorio;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Estudiante;
 


@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
	
	Boolean existsByNumeroDocumento(String numeroDocumento);
	
	List<Estudiante> findAll();
	
	Estudiante findByNumeroDocumento(String numeroDocumento);
	

	@Query(value = "select * from estudiante where id_sede = :id_sede and est_genero = :est_genero ", nativeQuery = true)
	List<Estudiante> listEstudianteGenero(@Param("est_genero") String est_genero, @Param("id_sede") Long id_sede);
	
}
