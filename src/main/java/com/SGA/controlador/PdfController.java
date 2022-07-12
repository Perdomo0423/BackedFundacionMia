package com.SGA.controlador;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Estudiante;
import com.SGA.repositorio.CuentaRepository;
import com.SGA.repositorio.PdfRepository;
import com.SGA.Pdf.CuentaCobro;
import com.SGA.Pdf.EstPdfExportar;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("web")
public class PdfController {
	@Autowired
	private PdfRepository studentRepo;
	
	@Autowired
	private CuentaRepository contratistaRepo;
	
	@GetMapping(value="/export/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> customersReport() throws IOException{
		List<Estudiante> customers =(List<Estudiante>) studentRepo.findAll();
		
		ByteArrayInputStream bis = EstPdfExportar.customerPDFReport(customers);
		
		HttpHeaders headers = new  HttpHeaders();
		headers.add("content-disposition", "inline; filename= datos-estudiantes.pdf");
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	
	
	
	@GetMapping(value="/export/cuenta", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> customersReportt() throws IOException{
		List<Contratista> customers =(List<Contratista>) contratistaRepo.findAll();
		
		ByteArrayInputStream bis = CuentaCobro.customerPDFReport(customers);
		
		HttpHeaders headers = new  HttpHeaders();
		headers.add("content-disposition", "inline; filename= datos-estudiantes.pdf");
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	

}
