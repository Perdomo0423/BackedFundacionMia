package com.SGA.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	 
	public Optional<Usuario> findByEmail(String email);

//	public Optional<Usuario> findById(Long id);

	 
	public Optional<Usuario> findByUsernameOrEmail (String username, String email);
	
	public Optional<Usuario> findByUsername(String username);

	public boolean existsByUsername(String usuername);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByUsernameAndPassword(String username, String password);
	/*
    @Query(value = "SELECT u FROM usuarios WHERE username=:usernameaaaaaaaaaaaaaaaaaaaaaa", nativeQuery=true)
    List<Usuario> eventsBySport(String username);
 	*/
    @Query("SELECT m "
			+ " FROM Usuario m "
			+ "WHERE m.username =?1 and m.password=?1")
	Optional<Usuario> findByUsernameAndPassword(String username, String password);




}
