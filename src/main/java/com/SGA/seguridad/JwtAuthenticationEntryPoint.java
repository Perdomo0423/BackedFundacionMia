package com.SGA.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// Manejaremos los errores de que no esta autorizado
		
		
		//-Este error significa que un usuario no esta autirizado
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		
	}
	
}
