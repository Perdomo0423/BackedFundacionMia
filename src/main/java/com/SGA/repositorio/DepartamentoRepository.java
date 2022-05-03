package com.SGA.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
	
	public boolean existsByCodigo(int codigo);
	
	public Departamento findByCodigo(int codigo);
	
	public Departamento findByNombreAndUnPaisIdPais(String nombre, Long  idPais);
}
