package com.SGA.controlador;

import java.io.IOException;

import com.SGA.servicio.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
	@Autowired
	private ExcelService excelService;

	@PostMapping
	public ResponseEntity<?> cargararchivos(@RequestParam("file") MultipartFile file, @RequestParam String type)
			throws IOException, InvalidFormatException {
		return this.excelService.saveExcel(file, type);
	}
}