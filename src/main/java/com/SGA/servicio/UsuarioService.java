package com.SGA.servicio;

import com.SGA.dto.StandarUserDto;
import com.SGA.dto.UsuarioDto;
import com.SGA.entidades.Contratista;
import com.SGA.entidades.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
	
//	 public Usuario save(Usuario usu);
	 public List<Usuario> all();
	  
    List<StandarUserDto> obtenerUsuarios();

    Usuario obtenerUsuarioPorId(Long id);

    Usuario actualizarContraseñaPorId(Long id);

    void guardarUsuario (com.SGA.entidades.Usuario usuario);
    

    Usuario save(com.SGA.entidades.Usuario usuario);

    HttpStatus updatePassword(UsuarioDto usuario);
    
//    HttpStatus actualizarContra( usuario);
    
    
}

 