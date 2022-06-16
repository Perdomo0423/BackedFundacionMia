package com.SGA.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SGA.Excel.ExcelFormatoCertificadoExport;
import com.SGA.entidades.Estudiante;
import com.SGA.repositorio.EstudianteRepository;

@Controller
@RequestMapping("/web")
public class ExcelCertificadoController {
	@RequestMapping("/home")
	public String homePage() {
		return "HomePage";
	}

	@Autowired
	private EstudianteRepository studentRepo;

	@RequestMapping("/export/certificado")
	@ResponseBody
	public String exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=formato--certificado-de-entrega-de-raciones-en-isntituciones-educativas.xlsx";

		response.setHeader(headerKey, headervalue);
		List<Estudiante> listStudent = studentRepo.findAll();
		ExcelFormatoCertificadoExport exp = new ExcelFormatoCertificadoExport(listStudent);
		exp.export(response);
		return "todo bien";
	}
	


	

}
