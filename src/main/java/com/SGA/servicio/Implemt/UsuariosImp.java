package com.SGA.servicio.Implemt;

import com.SGA.dto.StandarUserDto;
import com.SGA.dto.UsuarioDto;
import com.SGA.entidades.Contratista;
import com.SGA.entidades.Usuario;
import com.SGA.repositorio.UsuarioRepositorio;
import com.SGA.servicio.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import java.util.List;

@Service
public class UsuariosImp implements UsuarioService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

//    @Override
//    public List<StandarUserDto> obtenerUsuarios() {
//        return mapper.map(usuarioRepositorio.findAll(), new TypeToken<List<StandarUserDto>>(){}.getType());
//    }

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
	public List<Usuario> all() {		
		return this.usuarioRepositorio.findAll();
	}

    @Override
    public Usuario save(Usuario usu) {
        return usuarioRepositorio.save(usu);
    }

    
    @Override
    public HttpStatus updatePassword(UsuarioDto usuario) {
        HttpStatus httpStatus;
        try{
            if (!usuario.getNewPassword().equals(usuario.getConfirmPassword()))
                throw new Exception("¡La nueva contraseña y Confirmar contraseña no coinciden!");

            if (usuario.getCurrentPassword().equals(usuario.getNewPassword()))
                throw new Exception("¡La nueva contraseña debe ser diferente a la contraseña actual!");


            Usuario currentUser = usuarioRepositorio.findById(usuario.getId())
                    .orElseThrow(() -> new Exception("Usuario no encontrado"));

            boolean statusPass = passwordEncoder.matches(usuario.getCurrentPassword(), currentUser.getPassword());
            if(statusPass) {
                currentUser.setPassword(passwordEncoder.encode(usuario.getNewPassword()));
                usuarioRepositorio.save(currentUser);
                httpStatus = HttpStatus.OK;
            } else
                throw new Exception("¡La contraseña actual no coincide!");

        } catch (Exception exception) {
            System.out.println(exception);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return httpStatus;
    }

	@Override
	public List<StandarUserDto> obtenerUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
}
