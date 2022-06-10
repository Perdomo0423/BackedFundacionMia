package com.SGA.servicio.Implemt;

import com.SGA.dto.LoginDto;
import com.SGA.entidades.Usuario;
import com.SGA.excepciones.MensajeError;
import com.SGA.repositorio.UsuarioRepositorio;
import com.SGA.seguridad.JWTAuthResponseDto;
import com.SGA.seguridad.JwtTokenProvider;
import com.SGA.servicio.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthImpl implements AuthService {
    @Autowired
    private UsuarioRepositorio repositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<JWTAuthResponseDto> authenticateUser(LoginDto login) {
        Usuario user  = repositorio
                .findByUsernameOrEmail(login.getUsernameOrEmail(), login.getUsernameOrEmail())
                .orElseThrow(() -> new MensajeError("Correo o usuario de ingreso no existen en el sistema"));
        boolean passStatus = passwordEncoder.matches(login.getPassword(), user.getPassword());
        if(passStatus == false) throw new MensajeError("Contraseña incorrecta");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsernameOrEmail(), login.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtTokenProvider.generarToken(authentication);
        user.setPassword("**********");
        return  ResponseEntity.ok(new JWTAuthResponseDto(token, user));
    }
}
