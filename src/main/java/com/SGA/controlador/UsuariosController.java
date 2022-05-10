package com.SGA.controlador;

import com.SGA.dto.LoginDto;
import com.SGA.dto.UsuarioDto;
import com.SGA.entidades.Usuario;
import com.SGA.excepciones.MensajeError;
import com.SGA.repositorio.RolRepositorio;
import com.SGA.repositorio.UsuarioRepositorio;
import com.SGA.seguridad.JWTAuthResponseDto;
import com.SGA.seguridad.JwtTokenProvider;
import com.SGA.servicio.PersonaService;
import com.SGA.servicio.TipoDocumentoService;
import com.SGA.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;



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


    @PostMapping("/Verificacion")
    public ResponseEntity<JWTAuthResponseDto> vertificarContraseña(@RequestBody LoginDto logintDto){

        Usuario unUsuario  = usuarioRepositorio.findByUsernameOrEmail(logintDto.getUsernameOrEmail(), logintDto.getUsernameOrEmail()).
                orElseThrow(() -> new MensajeError("Correo o usuario de ingreso no existen en el sistema"));

        boolean contrasena = passwordEncoder.matches(logintDto.getPassword(), unUsuario.getPassword());
        if(contrasena==false) {
            throw new MensajeError("Contraseña incorrecta");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logintDto.getUsernameOrEmail(), logintDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Obtenemos el token de jwtTokenProvider
        String token= jwtTokenProvider.generarToken(authentication);
        unUsuario.setPassword("**********");
        return  ResponseEntity.ok(new JWTAuthResponseDto(token, unUsuario));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuarios(@RequestBody Usuario usuario, @PathVariable Long id){
        try{

            Usuario usuarioExistente = usuarioservice.actualizarContraseñaPorId(id);
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









