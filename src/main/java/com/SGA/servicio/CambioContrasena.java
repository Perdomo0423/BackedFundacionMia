package com.SGA.servicio;

import com.SGA.dto.UsuarioDto;
import com.SGA.entidades.Usuario;

public interface CambioContrasena {

    public Usuario usuario (UsuarioDto usuarioDto) throws Exception;
}
