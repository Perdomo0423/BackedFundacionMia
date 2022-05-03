package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{

}
