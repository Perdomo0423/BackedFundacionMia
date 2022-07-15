package com.SGA.Excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.unit.DataUnit;

import com.SGA.entidades.Estudiante;

import ch.qos.logback.core.joran.spi.DefaultClass;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.base.JRBasePrintLine;

public class ExcelFormatoCertificadoExport {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Estudiante> listStudent;
	
	
	public ExcelFormatoCertificadoExport(List<Estudiante> listStudent) {
		this.listStudent=listStudent;
		workbook = new XSSFWorkbook();
		
	}
	
	
	private void createCell(Row row,int columnCount, Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if(value instanceof Long) {
			cell.setCellValue((Long) value);
		}else if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	

	private void writeHeaderLine() throws IOException {

		InputStream inputStream1 = DefaultClass.class.getClassLoader().getResourceAsStream("Alimentos_para_aprender.jpg");
		byte[] inputImageBytes1 = IOUtils.toByteArray(inputStream1);
		int inputImagePictureID1 = workbook.addPicture(inputImageBytes1, Workbook.PICTURE_TYPE_JPEG);
		
		InputStream inputStream2 = DefaultClass.class.getClassLoader().getResourceAsStream("mine.png");
		byte[] inputImageBytes2 = IOUtils.toByteArray(inputStream2);
		int inputImagePictureID2 = workbook.addPicture(inputImageBytes2, Workbook.PICTURE_TYPE_PNG);
		
		InputStream inputStream3 = DefaultClass.class.getClassLoader().getResourceAsStream("pae.png");
		byte[] inputImageBytes3 = IOUtils.toByteArray(inputStream3);
		int inputImagePictureID3 = workbook.addPicture(inputImageBytes3, Workbook.PICTURE_TYPE_PNG);
		
		sheet=workbook.createSheet("Student");
	
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		CellStyle style2 = workbook.createCellStyle();
		CellStyle estilo = workbook.createCellStyle();
		CellStyle estilos = workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		XSSFFont fuent=workbook.createFont();
		XSSFFont fuente=workbook.createFont();
		
		
		XSSFDrawing imagen1 = (XSSFDrawing) sheet.createDrawingPatriarch();
		XSSFClientAnchor alimentos = new XSSFClientAnchor();
		alimentos.setCol1(0);
		alimentos.setCol2(1);
		alimentos.setRow1(2);
		alimentos.setRow2(4);
		
		XSSFDrawing imagen2 = (XSSFDrawing) sheet.createDrawingPatriarch();
		XSSFClientAnchor mineducacion = new XSSFClientAnchor();
		mineducacion.setCol1(1);
		mineducacion.setCol2(3);
		mineducacion.setRow1(2);
		mineducacion.setRow2(4);
		

		XSSFDrawing imagen3 = (XSSFDrawing) sheet.createDrawingPatriarch();
		XSSFClientAnchor pae = new XSSFClientAnchor();
		pae.setCol1(3);
		pae.setCol2(4);
		pae.setRow1(2);
		pae.setRow2(4);
		
		
		imagen1.createPicture(alimentos, inputImagePictureID1);
		imagen2.createPicture(mineducacion, inputImagePictureID2);
		imagen3.createPicture(pae, inputImagePictureID3);
		
		
		
		font.setFontHeight(11);
		fuent.setFontHeight(11);
		fuent.setBold(true);
		style.setFont(font);

		style2.setFont(fuent);
		estilos.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
	    estilos.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
	    style2.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
	    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    style2.setBorderLeft(BorderStyle.MEDIUM);
	    style2.setRightBorderColor(IndexedColors.BLACK.getIndex());
	 
		estilos.setAlignment(HorizontalAlignment.CENTER);
		estilos.setFont(fuent);
	
		

		createCell(row,0,"",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
		sheet.addMergedRegion(new CellRangeAddress(0,82,13,1000));
		sheet.addMergedRegion(new CellRangeAddress(1,3,0,3));
		sheet.addMergedRegion(new CellRangeAddress(83,1000,0,1000));
		
		
		

		
		row=sheet.createRow(1);
        createCell(row,4,"CERTIFICADO DE ENTREGA DE RACIONES A INSTITUCIONES EDUCATIVAS",style);
        sheet.addMergedRegion(new CellRangeAddress(1,3,4,12));
     


        sheet.addMergedRegion(new CellRangeAddress(4,4,0,12));


		row=sheet.createRow(5);
        createCell(row, 0, "DATOS GENERALES", estilos);
        sheet.addMergedRegion(new CellRangeAddress(5,5,0,12));


		row=sheet.createRow(6);
        createCell(row, 0, "OPERADOR", style);
        createCell(row, 6, "CONTRATO N°", style);
        sheet.addMergedRegion(new CellRangeAddress(6,6,1,5));
        sheet.addMergedRegion(new CellRangeAddress(6,6,7,12));


        sheet.addMergedRegion(new CellRangeAddress(7,7,0,12));
        
        
        row=sheet.createRow(8);
        createCell(row, 0, "INSTITUCION O CENTRO EDUCATIVO", style);
        createCell(row, 6, "CÓDIGO DANE", style);
        sheet.addMergedRegion(new CellRangeAddress(8,8,1,5));
        sheet.addMergedRegion(new CellRangeAddress(8,8,7,12));
        
        
        row=sheet.createRow(9);
        createCell(row, 0, "DEPARTAMENTO:", style);
        createCell(row, 6, "CÓDIGO DANE", style);
        sheet.addMergedRegion(new CellRangeAddress(9,9,1,5));
        sheet.addMergedRegion(new CellRangeAddress(9,9,7,12));
        
        
        row=sheet.createRow(10);
        createCell(row, 0, "MUNICIPIO:", style);
        createCell(row, 6, "CÓDIGO DANE", style);
        sheet.addMergedRegion(new CellRangeAddress(10,10,1,5));
        sheet.addMergedRegion(new CellRangeAddress(10,10,7,12));
        
      
         
         row=sheet.createRow(11);
         createCell(row, 0, "FECHA DE EJECUCION ", style);
         createCell(row, 1, "Desde", style);
         createCell(row, 4, "Hasta", style);
         sheet.addMergedRegion(new CellRangeAddress(11,11,2,3));
         sheet.addMergedRegion(new CellRangeAddress(11,11,4,5));
         sheet.addMergedRegion(new CellRangeAddress(11,11,6,12));
     	
         
         row=sheet.createRow(12);
          createCell(row, 0, "NOMBRE RECTOR:", style);
          sheet.addMergedRegion(new CellRangeAddress(12,12,1,12));
          sheet.addMergedRegion(new CellRangeAddress(13,14,0,12));
          
          
          row=sheet.createRow(15);
          createCell(row, 0, "CERTIFICACION", estilos);
          sheet.addMergedRegion(new CellRangeAddress(15,15,0,12));
          
          
          row=sheet.createRow(16);
          createCell(row, 0, "El suscrito Rector de la Institución Educativa citada en el encabezado, certifica que se entregaron las siguientes raciones, ", style);
          sheet.addMergedRegion(new CellRangeAddress(16,16,0,12));

          
          row=sheet.createRow(17);
          createCell(row, 0, " en las fechas señaladas y de acuerdo con la siguiente distribución:", style);
          sheet.addMergedRegion(new CellRangeAddress(17,17,0,12));
          
          sheet.addMergedRegion(new CellRangeAddress(18,19,0,12));
          
          row=sheet.createRow(20);
          createCell(row, 0, "NOMBRE DEL ESTABLECIMIENTO EDUCATIVO",estilos);
          createCell(row, 1, "TIPO RACIÓN",estilos);
          createCell(row, 2, "ENTREGADO",estilos);
          sheet.addMergedRegion(new CellRangeAddress(21,21,2,4));
          sheet.addMergedRegion(new CellRangeAddress(21,21,5,6));
          sheet.addMergedRegion(new CellRangeAddress(20,21,1,1));
          sheet.addMergedRegion(new CellRangeAddress(20,20,2,12));
          
          row=sheet.createRow(21);
          createCell(row, 0, "O CENTRO EDUCATIVO", estilos);
          createCell(row, 2, "N°RACIONES POR DIA", style2);
          createCell(row, 5, "N° DIAS ATENDIDOS", style2);
          createCell(row, 7, "TOTAL RACIONES", style2);
          createCell(row, 8, "NOVEDADES", style2);
          sheet.addMergedRegion(new CellRangeAddress(21,21,8,12));
         
          
          
          row=sheet.createRow(22);
          createCell(row, 1, "CAJM/CAJT          ", style);
          sheet.addMergedRegion(new CellRangeAddress(22,22,2,4));
          sheet.addMergedRegion(new CellRangeAddress(22,22,5,6));
          sheet.addMergedRegion(new CellRangeAddress(22,22,8,12));
        
          
          row=sheet.createRow(23);
          createCell(row, 1, "ALMUERZO          ", style);
          sheet.addMergedRegion(new CellRangeAddress(23,23,2,4));
          sheet.addMergedRegion(new CellRangeAddress(23,23,5,6));
          sheet.addMergedRegion(new CellRangeAddress(23,23,8,12));
          
          
          row=sheet.createRow(24);
          createCell(row, 1, "RI          ", style);
          sheet.addMergedRegion(new CellRangeAddress(24,24,2,4));
          sheet.addMergedRegion(new CellRangeAddress(24,24,5,6));
          sheet.addMergedRegion(new CellRangeAddress(24,24,8,12));
          
          
          row=sheet.createRow(25);
          createCell(row, 1, "DESAYUNO", style);
          sheet.addMergedRegion(new CellRangeAddress(25,25,2,4));
          sheet.addMergedRegion(new CellRangeAddress(25,25,5,6));
          sheet.addMergedRegion(new CellRangeAddress(25,25,8,12));
          
          
          row=sheet.createRow(26);
          createCell(row, 1, "COMIDA", style);
          sheet.addMergedRegion(new CellRangeAddress(26,26,2,4));
          sheet.addMergedRegion(new CellRangeAddress(26,26,5,6));
          sheet.addMergedRegion(new CellRangeAddress(26,26,8,12));
          
          
          
          row=sheet.createRow(27);
          createCell(row, 1, "RACION CASA", style);
          sheet.addMergedRegion(new CellRangeAddress(27,27,2,4));
          sheet.addMergedRegion(new CellRangeAddress(27,27,5,6));
          sheet.addMergedRegion(new CellRangeAddress(27,27,8,12));
          
          
          row=sheet.createRow(28);
          createCell(row, 1, "RTC", style);
          sheet.addMergedRegion(new CellRangeAddress(28,28,2,4));
          sheet.addMergedRegion(new CellRangeAddress(28,28,5,6));
          sheet.addMergedRegion(new CellRangeAddress(28,28,8,12));
          
          
          row=sheet.createRow(29);
          createCell(row, 1, "BONO", style);
          sheet.addMergedRegion(new CellRangeAddress(29,29,2,4));
          sheet.addMergedRegion(new CellRangeAddress(29,29,5,6));
          sheet.addMergedRegion(new CellRangeAddress(29,29,8,12));
          
          sheet.addMergedRegion(new CellRangeAddress(22,29,0,0));
          
          row=sheet.createRow(30);
          createCell(row, 1, "CAJM/CAJT          ", style);
          sheet.addMergedRegion(new CellRangeAddress(30,30,2,4));
          sheet.addMergedRegion(new CellRangeAddress(30,30,5,6));
          sheet.addMergedRegion(new CellRangeAddress(30,30,8,12));
        
          
          row=sheet.createRow(31);
          createCell(row, 1, "ALMUERZO          ", style);
          sheet.addMergedRegion(new CellRangeAddress(31,31,2,4));
          sheet.addMergedRegion(new CellRangeAddress(31,31,5,6));
          sheet.addMergedRegion(new CellRangeAddress(31,31,8,12));
          
          
          row=sheet.createRow(32);
          createCell(row, 1, "RI          ", style);
          sheet.addMergedRegion(new CellRangeAddress(32,32,2,4));
          sheet.addMergedRegion(new CellRangeAddress(32,32,5,6));
          sheet.addMergedRegion(new CellRangeAddress(32,32,8,12));
          
          
          row=sheet.createRow(33);
          createCell(row, 1, "DESAYUNO", style);
          sheet.addMergedRegion(new CellRangeAddress(33,33,2,4));
          sheet.addMergedRegion(new CellRangeAddress(33,33,5,6));
          sheet.addMergedRegion(new CellRangeAddress(33,33,8,12));
          
          
          row=sheet.createRow(34);
          createCell(row, 1, "COMIDA", style);
          sheet.addMergedRegion(new CellRangeAddress(34,34,2,4));
          sheet.addMergedRegion(new CellRangeAddress(34,34,5,6));
          sheet.addMergedRegion(new CellRangeAddress(34,34,8,12));
          
          
          row=sheet.createRow(35);
          createCell(row, 1, "RACION CASA", style);
          sheet.addMergedRegion(new CellRangeAddress(35,35,2,4));
          sheet.addMergedRegion(new CellRangeAddress(35,35,5,6));
          sheet.addMergedRegion(new CellRangeAddress(35,35,8,12));
          
          
          row=sheet.createRow(36);
          createCell(row, 1, "RTC", style);
          sheet.addMergedRegion(new CellRangeAddress(36,36,2,4));
          sheet.addMergedRegion(new CellRangeAddress(36,36,5,6));
          sheet.addMergedRegion(new CellRangeAddress(36,36,8,12));
          

          
          row=sheet.createRow(37);
          createCell(row, 1, "BONO", style);
          sheet.addMergedRegion(new CellRangeAddress(37,37,2,4));
          sheet.addMergedRegion(new CellRangeAddress(37,37,5,6));
          sheet.addMergedRegion(new CellRangeAddress(37,37,8,12));
//
          sheet.addMergedRegion(new CellRangeAddress(30,37,0,0));
          
          row=sheet.createRow(38);
          createCell(row, 1, "CAJM/CAJT          ", style);
          sheet.addMergedRegion(new CellRangeAddress(38,38,2,4));
          sheet.addMergedRegion(new CellRangeAddress(38,38,5,6));
          sheet.addMergedRegion(new CellRangeAddress(38,38,8,12));
        
          
          row=sheet.createRow(39);
          createCell(row, 1, "ALMUERZO          ", style);
          sheet.addMergedRegion(new CellRangeAddress(39,39,2,4));
          sheet.addMergedRegion(new CellRangeAddress(39,39,5,6));
          sheet.addMergedRegion(new CellRangeAddress(39,39,8,12));
          
          
          row=sheet.createRow(40);
          createCell(row, 1, "RI          ", style);
          sheet.addMergedRegion(new CellRangeAddress(40,40,2,4));
          sheet.addMergedRegion(new CellRangeAddress(40,40,5,6));
          sheet.addMergedRegion(new CellRangeAddress(40,40,8,12));
          
          
          row=sheet.createRow(41);
          createCell(row, 1, "DESAYUNO", style);
          sheet.addMergedRegion(new CellRangeAddress(41,41,2,4));
          sheet.addMergedRegion(new CellRangeAddress(41,41,5,6));
          sheet.addMergedRegion(new CellRangeAddress(41,41,8,12));
          
          
          row=sheet.createRow(42);
          createCell(row, 1, "COMIDA", style);
          sheet.addMergedRegion(new CellRangeAddress(42,42,2,4));
          sheet.addMergedRegion(new CellRangeAddress(42,42,5,6));
          sheet.addMergedRegion(new CellRangeAddress(42,42,8,12));
          
          
          row=sheet.createRow(43);
          createCell(row, 1, "RACION CASA", style);
          sheet.addMergedRegion(new CellRangeAddress(43,43,2,4));
          sheet.addMergedRegion(new CellRangeAddress(43,43,5,6));
          sheet.addMergedRegion(new CellRangeAddress(43,43,8,12));
          
          
          row=sheet.createRow(44);
          createCell(row, 1, "RTC", style);
          sheet.addMergedRegion(new CellRangeAddress(44,44,2,4));
          sheet.addMergedRegion(new CellRangeAddress(44,44,5,6));
          sheet.addMergedRegion(new CellRangeAddress(44,44,8,12));
          
          
          row=sheet.createRow(45);
          createCell(row, 1, "BONO", style);
          sheet.addMergedRegion(new CellRangeAddress(45,45,2,4));
          sheet.addMergedRegion(new CellRangeAddress(45,45,5,6));
          sheet.addMergedRegion(new CellRangeAddress(45,45,8,12));
   
          sheet.addMergedRegion(new CellRangeAddress(38,45,0,0));
 
          row=sheet.createRow(46);
          createCell(row, 0, "TOTAL", style);
          sheet.addMergedRegion(new CellRangeAddress(46,46,0,1));
          sheet.addMergedRegion(new CellRangeAddress(46,46,2,12));
          
          row=sheet.createRow(47);
          createCell(row, 0, "CAJM/CAJT = Complemento Alimentario Jornada Mañana  /  Complemento Alimentario Jornada Tarde", style);
          sheet.addMergedRegion(new CellRangeAddress(47,47,0,12));
    
          
          
          row=sheet.createRow(48);
          createCell(row, 0, "ALMUERZO = Almuerzo", style);
          sheet.addMergedRegion(new CellRangeAddress(48,48,0,12));

          
          
          row=sheet.createRow(49);
          createCell(row, 0, "RI: Ración Industrializada",style);
          sheet.addMergedRegion(new CellRangeAddress(49,49,0,12));
          
          row=sheet.createRow(50);
          createCell(row, 0, "DESAYUNO: Desayuno",style);
          sheet.addMergedRegion(new CellRangeAddress(50,50,0,12));
          
          row=sheet.createRow(51);
          createCell(row, 0, "COMIDA: Comida",style);
          sheet.addMergedRegion(new CellRangeAddress(51,51,0,12));
          
          row=sheet.createRow(52);
          createCell(row, 0, "RPC: Racion Preparada en Casa",style);
          sheet.addMergedRegion(new CellRangeAddress(52,52,0,12));
          
          row=sheet.createRow(53);
          createCell(row, 0, "RTC: Racio Tranportada Caliente",style);
          sheet.addMergedRegion(new CellRangeAddress(53,53,0,12));
          
          row=sheet.createRow(54);
          createCell(row, 0, "BONO: Bono",style);
          sheet.addMergedRegion(new CellRangeAddress(54,54,0,12));
          
          sheet.addMergedRegion(new CellRangeAddress(55,55,0,12));
          sheet.addMergedRegion(new CellRangeAddress(56,56,1,3));
          sheet.addMergedRegion(new CellRangeAddress(56,56,4,6));
          sheet.addMergedRegion(new CellRangeAddress(56,56,7,9));
          sheet.addMergedRegion(new CellRangeAddress(56,56,10,12));
          
          row=sheet.createRow(56);
          fuente.setFontHeight(9);
          fuente.setBold(true);
  		  estilo.setFont(fuente);
  		  estilo.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
  		  estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          createCell(row, 0, "DESCRIPCION",estilos);
          createCell(row, 1, "TOTAL RACIONES ENTREGADAS COMPLE JM/JT",estilo);
          createCell(row, 4, "TOTAL RACIONES ENTREGADAS ALMUERZOS",estilo);
          createCell(row, 7, "TOTAL RACIONES ENTREGADAS RI",estilo);
          createCell(row, 10, "No. TITULARES DE DERECHO",estilo);

          row=sheet.createRow(57);
          createCell(row, 0, "POBLACIÓN EN CONDICIÓN DE DISCAPACIDAD",style);
          sheet.addMergedRegion(new CellRangeAddress(57,57,1,3));
          sheet.addMergedRegion(new CellRangeAddress(57,57,4,6));
          sheet.addMergedRegion(new CellRangeAddress(57,57,7,9));
          sheet.addMergedRegion(new CellRangeAddress(57,57,10,12));
          
          row=sheet.createRow(58);
          createCell(row, 0, "POBLACIÓN VÍCTIMA DEL CONFLICTO ARMADO",style);
          sheet.addMergedRegion(new CellRangeAddress(58,58,1,3));
          sheet.addMergedRegion(new CellRangeAddress(58,58,4,6));
          sheet.addMergedRegion(new CellRangeAddress(58,58,7,9));
          sheet.addMergedRegion(new CellRangeAddress(58,58,10,12));
          
          row=sheet.createRow(59);
          createCell(row, 0, "COMUNIDADES ÉTNICAS",style);
          sheet.addMergedRegion(new CellRangeAddress(59,59,1,3));
          sheet.addMergedRegion(new CellRangeAddress(59,59,4,6));
          sheet.addMergedRegion(new CellRangeAddress(59,59,7,9));
          sheet.addMergedRegion(new CellRangeAddress(59,59,10,12));
          
          row=sheet.createRow(60);
          createCell(row, 0, "POBLACIÓN MAYORITARIA",style);
          sheet.addMergedRegion(new CellRangeAddress(60,60,1,3));
          sheet.addMergedRegion(new CellRangeAddress(60,60,4,6));
          sheet.addMergedRegion(new CellRangeAddress(60,60,7,9));
          sheet.addMergedRegion(new CellRangeAddress(60,60,10,12));
          
          row=sheet.createRow(61);
          createCell(row, 0, "GRAN TOTAL",style);
          sheet.addMergedRegion(new CellRangeAddress(61,61,1,3));
          sheet.addMergedRegion(new CellRangeAddress(61,61,4,6));
          sheet.addMergedRegion(new CellRangeAddress(61,61,7,9));
          sheet.addMergedRegion(new CellRangeAddress(61,61,10,12));
//          
          sheet.addMergedRegion(new CellRangeAddress(62,63,0,12));

          
          row=sheet.createRow(64);
          createCell(row, 0, "OBSERVACIONES",estilos);
          sheet.addMergedRegion(new CellRangeAddress(65,69,0,12));       
          sheet.addMergedRegion(new CellRangeAddress(64,64,0,12));
          sheet.addMergedRegion(new CellRangeAddress(70,70,0,12));

          
//          
//          
          row=sheet.createRow(71);
          createCell(row, 0, "La presente certificación se expide como soporte de pago y con base en el registro diario de Titulares de Derecho, que se diligencia en cada Institución Educativa atendida.",style);
          sheet.addMergedRegion(new CellRangeAddress(71,72,0,12));
          sheet.addMergedRegion(new CellRangeAddress(73,73,0,12));


//          
          row=sheet.createRow(74);
          createCell(row, 0, "PARA CONSTANCIA SE FIRMA EN:",style);
          sheet.addMergedRegion(new CellRangeAddress(74,74,0,12));
//          
//          
          row=sheet.createRow(75);
          createCell(row, 0, "FECHA",style);
          createCell(row, 1, "DIA",style);
          createCell(row, 6, "DEL",style);
          createCell(row, 7, "AÑO",style);
          sheet.addMergedRegion(new CellRangeAddress(75,75,2,5));
          sheet.addMergedRegion(new CellRangeAddress(75,75,7,8));
          sheet.addMergedRegion(new CellRangeAddress(75,75,9,12));
//          
//          
          row=sheet.createRow(76);
          createCell(row, 0, "FIRMA DEL RECTOR",style);
          sheet.addMergedRegion(new CellRangeAddress(76,81,0,12));
//
//          
          row=sheet.createRow(82);
          createCell(row, 0, "NOMBRES Y APELLIDOS DEL RECTOR",style);
          sheet.addMergedRegion(new CellRangeAddress(82,82,1,12));
//                          
                     
	}
	
	
	public void export(HttpServletResponse response) throws IOException{
		writeHeaderLine();
		
	
		
		ServletOutputStream outputStream=response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	
}
