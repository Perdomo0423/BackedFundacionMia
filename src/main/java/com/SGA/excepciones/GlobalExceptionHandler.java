package com.SGA.excepciones;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.SGA.dto.ErrorDetalles;
import com.SGA.utilerias.BlogAppException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ResourceNotFoundException.class) //Esta anotación es la encargada de manejar las excepciones que se hallan detallado
	public ResponseEntity<ErrorDetalles> manejarResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
		ErrorDetalles errorDetaller = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetaller, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BlogAppException.class) //Esta anotación es la encargada de manejar las excepciones que se hallan detallado
	public ResponseEntity<ErrorDetalles> manejarBlogAppException(BlogAppException exception, WebRequest webRequest){
		ErrorDetalles errorDetaller = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetaller, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class) //Esta anotación es la encargada de manejar las excepciones que se hallan detallado
	public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception, WebRequest webRequest){
		ErrorDetalles errorDetaller = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetaller, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String nombreCampo = ((FieldError)error).getField();
			String mensaje = error.getDefaultMessage();
			errores.put(nombreCampo, mensaje);
		});
		return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
		
	}
}