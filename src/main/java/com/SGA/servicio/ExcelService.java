package com.SGA.servicio;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {
    ResponseEntity<?> saveExcel(MultipartFile file, String type) throws IOException, InvalidFormatException;
}
