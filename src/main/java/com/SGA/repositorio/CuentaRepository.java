package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.SGA.entidades.Contratista;

public interface CuentaRepository  extends CrudRepository<Contratista, Long>{

}
