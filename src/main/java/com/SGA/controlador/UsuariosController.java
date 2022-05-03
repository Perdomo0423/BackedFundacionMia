package com.SGA.controlador;

import com.SGA.dto.UsuarioDto;
import com.SGA.entidades.Usuario;
import com.SGA.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios(){
        return usuarioservice.findALl();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Usuario> obtenerUsuarios (@PathVariable Long id){
        try{
            Usuario usuario = usuarioservice.obtenerUsuarioPorId(id);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

    }


//    @PutMapping("/prueba/")
//    public ResponseEntity actualizar(@Valid @RequestBody UsuarioDto usuarioDto, Error error){
//
//    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuarios(@Valid @RequestBody UsuarioDto usuarioDto, @RequestBody Usuario usuario, @PathVariable Long id){
        try{

            Usuario usuarioExistente = usuarioservice.actualizarContraseñaPorId(id);
            usuarioExistente.setPassword(usuarioDto.getCurrentPassword());
            usuarioExistente.setPassword(usuarioDto.getConfirmPassword());
            usuarioExistente.setPassword(usuarioDto.getNewPassword());
            usuarioExistente.setPassword(usuario.getPassword());
            usuarioExistente.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setUsername(usuario.getUsername());
            usuarioservice.guardarUsuario(usuarioExistente);
            return new ResponseEntity<Usuario>(HttpStatus.OK);

        } catch (Exception exception){
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
    }







}









