package com.SGA.servicio.Implemt;

import com.SGA.dto.UsuarioDto;
import com.SGA.entidades.Usuario;
import com.SGA.repositorio.UsuarioRepositorio;
import com.SGA.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosImp implements UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> findALl() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id).get();
    }

    @Override
    public Usuario actualizarContraseñaPorId(Long id) {
        return usuarioRepositorio.findById(id).get();
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
       usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }


    @Override
    public Usuario ChangePassword(UsuarioDto usuarioDto) throws Exception {
        Usuario usuario = usuarioRepositorio.findById(usuarioDto.getId()).orElseThrow(() -> new Exception("Usuario no encontrado en Cambiar contraseña -"+this.getClass().getName()));


        if (usuarioDto.getCurrentPassword().equals(usuario.getPassword())){
            throw new Exception("Contraseña actual incorrecta");
        }

        if (usuarioDto.getCurrentPassword().equals(usuarioDto.getNewPassword())){
            throw new Exception("¡La nueva contraseña debe ser diferente a la contraseña actual!");
        }

        if (!usuarioDto.getNewPassword().equals(usuarioDto.getConfirmPassword())){
            throw new Exception("¡La nueva contraseña y Confirmar contraseña no coinciden!");
        }

        usuario.setPassword(usuarioDto.getNewPassword());
        return usuarioRepositorio.save(usuario);
    }

//    @Override
//    public Usuario confirmaPassword(Long id) throws Exception {
//        Usuario usuario = usuarioRepositorio.findById();
//
//        if (usuario.getPassword().equals(usuario.getConfirmPassword()))
//        {
//            throw new Exception("La contraseñas es incorrecta");
//        }
//        return null;
//    }
}
