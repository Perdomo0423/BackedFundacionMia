package com.SGA.servicio;

import com.SGA.dto.UsuarioDto;
import com.SGA.entidades.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UsuarioService {



    public List<Usuario> findALl();

    public Usuario obtenerUsuarioPorId(Long id);

    public Usuario actualizarContraseñaPorId(Long id);

    public void guardarUsuario (com.SGA.entidades.Usuario usuario);

    Usuario save(com.SGA.entidades.Usuario usuario);

    public Usuario ChangePassword (UsuarioDto usuarioDto) throws Exception;

//    public Usuario confirmaPassword(Long id) throws Exception;

}
