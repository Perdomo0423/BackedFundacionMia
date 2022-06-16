package com.SGA.controlador;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPdfController implements ErrorController{
	@GetMapping("*/error")
	public String customError() {
		return "no salio perrro llore";
	}
	
	public String getErrorPath() {
		return "/error";
	}

}
