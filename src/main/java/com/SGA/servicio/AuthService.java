package com.SGA.servicio;

import com.SGA.dto.LoginDto;
import com.SGA.seguridad.JWTAuthResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<JWTAuthResponseDto> authenticateUser(LoginDto login);
}
