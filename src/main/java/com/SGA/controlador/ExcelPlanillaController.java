package com.SGA.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SGA.Excel.ExcelFormatoPlanillaExport;

import com.SGA.entidades.Estudiante;
import com.SGA.repositorio.EstudianteRepository;

@Controller
@RequestMapping("/web")
public class ExcelPlanillaController {
	

	@Autowired
	private EstudianteRepository studentRepo;

	@RequestMapping("/export/planilla")
	@ResponseBody
	public String exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=planilla estudiantil.xlsx";

		response.setHeader(headerKey, headervalue);
		List<Estudiante> listStudent = studentRepo.findAll();
		ExcelFormatoPlanillaExport exp = new ExcelFormatoPlanillaExport(listStudent);
		exp.export(response);
		return "todo bien";
	}
	


	

}
