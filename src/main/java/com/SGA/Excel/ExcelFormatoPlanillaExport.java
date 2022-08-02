package com.SGA.Excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.sl.usermodel.VerticalAlignment;
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
import org.apache.poi.xwpf.usermodel.VerticalAlign;

import com.SGA.entidades.Estudiante;

import ch.qos.logback.core.joran.spi.DefaultClass;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

public class ExcelFormatoPlanillaExport {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Estudiante> listStudent;
	
	
	public ExcelFormatoPlanillaExport(List<Estudiante> listStudent) {
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
	private void writeHeaderLine() throws IOException{
		
		InputStream inputStream1 = DefaultClass.class.getClassLoader().getResourceAsStream("Alimentos_para_aprender.jpg");
		byte[] inputImageBytes1 = IOUtils.toByteArray(inputStream1);
		int inputImagePictureID1 = workbook.addPicture(inputImageBytes1, Workbook.PICTURE_TYPE_JPEG);
		
		InputStream inputStream2 = DefaultClass.class.getClassLoader().getResourceAsStream("mine.png");
		byte[] inputImageBytes2 = IOUtils.toByteArray(inputStream2);
		int inputImagePictureID2 = workbook.addPicture(inputImageBytes2, Workbook.PICTURE_TYPE_PNG);
		
		InputStream inputStream3 = DefaultClass.class.getClassLoader().getResourceAsStream("pae.png");
		byte[] inputImageBytes3 = IOUtils.toByteArray(inputStream3);
		int inputImagePictureID3 = workbook.addPicture(inputImageBytes3, Workbook.PICTURE_TYPE_PNG);
//		
		
//		firmas
		
		InputStream inputStream4 = DefaultClass.class.getClassLoader().getResourceAsStream("firma1.png");
		byte[] inputImageBytes4 = IOUtils.toByteArray(inputStream4);
		int inputImagePictureID4 = workbook.addPicture(inputImageBytes4, Workbook.PICTURE_TYPE_PNG);
		
		InputStream inputStream5 = DefaultClass.class.getClassLoader().getResourceAsStream("firma2.png");
		byte[] inputImageBytes5= IOUtils.toByteArray(inputStream5);
		int inputImagePictureID5 = workbook.addPicture(inputImageBytes5, Workbook.PICTURE_TYPE_PNG);
		
		
		InputStream inputStream6 = DefaultClass.class.getClassLoader().getResourceAsStream("firma3.png");
		byte[] inputImageBytes6= IOUtils.toByteArray(inputStream6);
		int inputImagePictureID6 = workbook.addPicture(inputImageBytes6, Workbook.PICTURE_TYPE_PNG);
		
		
		
		
		
		
		sheet=workbook.createSheet("Student");
		
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		CellStyle style2 = workbook.createCellStyle();
		CellStyle style3 = workbook.createCellStyle();
		CellStyle style4 = workbook.createCellStyle();
		CellStyle style5 = workbook.createCellStyle();
		CellStyle style6 = workbook.createCellStyle();
		CellStyle style7 = workbook.createCellStyle();
		CellStyle style8 = workbook.createCellStyle();
		CellStyle style9 = workbook.createCellStyle();
		CellStyle style10 = workbook.createCellStyle();
		CellStyle style11 = workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		XSSFFont fuente=workbook.createFont();
		XSSFFont fuenteD=workbook.createFont();
		
		
		
		
		XSSFDrawing imagen1 = (XSSFDrawing) sheet.createDrawingPatriarch();
		XSSFClientAnchor alimentos = new XSSFClientAnchor();
		alimentos.setCol1(1);
		alimentos.setCol2(2);
		alimentos.setRow1(1);
		alimentos.setRow2(3);
		
		XSSFDrawing imagen2 = (XSSFDrawing) sheet.createDrawingPatriarch();
		XSSFClientAnchor mineducacion = new XSSFClientAnchor();
		mineducacion.setCol1(2);
		mineducacion.setCol2(4);
		mineducacion.setRow1(1);
		mineducacion.setRow2(3);
		

		XSSFDrawing imagen3 = (XSSFDrawing) sheet.createDrawingPatriarch();
		XSSFClientAnchor pae = new XSSFClientAnchor();
		pae.setCol1(4);
		pae.setCol2(5);
		pae.setRow1(1);
		pae.setRow2(3);
		
		
//		firmas
		
		XSSFDrawing imagen4 = (XSSFDrawing) sheet.createDrawingPatriarch();
		XSSFClientAnchor firma1 = new XSSFClientAnchor();
		firma1.setCol1(4);
		firma1.setCol2(5);
		firma1.setRow1(46);
		firma1.setRow2(48);

		XSSFDrawing imagen5 = (XSSFDrawing) sheet.createDrawingPatriarch();
		XSSFClientAnchor firma2 = new XSSFClientAnchor();
		firma2.setCol1(9);
		firma2.setCol2(15);
		firma2.setRow1(46);
		firma2.setRow2(48);
		
		XSSFDrawing imagen6 = (XSSFDrawing) sheet.createDrawingPatriarch();
		XSSFClientAnchor firma3 = new XSSFClientAnchor();
		firma3.setCol1(32);
		firma3.setCol2(40);
		firma3.setRow1(46);
		firma3.setRow2(48);
		
		
		

		imagen1.createPicture(alimentos, inputImagePictureID1);
		imagen2.createPicture(mineducacion, inputImagePictureID2);
		imagen3.createPicture(pae, inputImagePictureID3);
		
//		firmas
		imagen4.createPicture(firma1, inputImagePictureID4);
		imagen5.createPicture(firma2, inputImagePictureID5);
		imagen6.createPicture(firma3, inputImagePictureID6);
		
		fuenteD.setFontHeight(12);
		fuenteD.setBold(true);
		style10.setAlignment(HorizontalAlignment.CENTER_SELECTION);

		style10.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    style10.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style10.setBorderLeft(BorderStyle.MEDIUM);
		style10.setBorderRight(BorderStyle.MEDIUM);
		style10.setFont(fuenteD);
		
		
		
		
		
	
		
		style3.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		
	    style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    style5.setBorderBottom(BorderStyle.MEDIUM);
	    style5.setBorderTop(BorderStyle.MEDIUM);
		font.setBold(true);
		fuente.setBold(true);
		font.setFontHeight(8);
		style7.setFont(fuente);
		style7.setBorderRight(BorderStyle.MEDIUM);
		style8.setFont(fuente);
		style8.setBorderLeft(BorderStyle.MEDIUM);
		style9.setFont(fuente);

		style9.setBorderBottom(BorderStyle.MEDIUM);
		style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		style.setFont(font);
		style2.setFont(fuente);
		style3.setFont(font);
		style4.setFont(fuente);
		fuente.setFontHeight(8);
		style6.setBorderBottom(BorderStyle.MEDIUM);
		style6.setBorderRight(BorderStyle.MEDIUM);
		style6.setBorderTop(BorderStyle.MEDIUM);
		style3.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		style3.setBorderRight(BorderStyle.MEDIUM);
		style4.setBorderRight(BorderStyle.MEDIUM);
		style4.setBorderLeft(BorderStyle.MEDIUM);
		style4.setRotation((short)90);
		style7.setRotation((short)90);
		style8.setRotation((short)90);
		style9.setRotation((short)90);
		style10.setRotation((short)90);
		style11.setRotation((short)90);
		style4.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    style4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    style7.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    style7.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    style8.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    style8.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		
		
		
		
		
		row=sheet.createRow(0);
        createCell(row,6,"REGISTRO Y CONTROL DE ASISTENCIA",style2);
        sheet.addMergedRegion(new CellRangeAddress(0,2,0,5));
        sheet.addMergedRegion(new CellRangeAddress(0,2,6,46)); 
        
        row=sheet.createRow(3);
        createCell(row,0,"DEPARTAMENTO/CODIGO DANE:HUILA/41",style2);
        createCell(row,3,"MUNICIPIO/CODIGO DANE:",style2);
        createCell(row,5,"INSTITUCION EDUCATIVA/CODIGO DANE:",style2);
        createCell(row,15,"SEDE EDUCATIVA/CODIGO DANE:",style2);
        createCell(row,36,"MES/AÑO:",style2);
        sheet.addMergedRegion(new CellRangeAddress(3,4,0,2));
        sheet.addMergedRegion(new CellRangeAddress(3,4,3,4));
        sheet.addMergedRegion(new CellRangeAddress(3,4,5,14));
        sheet.addMergedRegion(new CellRangeAddress(3,4,15,35));
        sheet.addMergedRegion(new CellRangeAddress(3,4,36,46));
       
                     
        
        row=sheet.createRow(5);
        createCell(row,0,"CUPOS ALMUERZOS:",style2);
        createCell(row,3,"RACIONES PROGRAMADAS ALMUERZOS:",style2);
        createCell(row,5,"CUPOS COMPLEMENTO ALIMENTARIO:",style2);
        createCell(row,7,"RACIONES PROGRAMADAS C.A:AM____PM____",style2);
        createCell(row,18,"DESAYUNO:",style2);
        createCell(row,25,"OPERADOR:",style2);
        createCell(row,36,"No OPERACION:",style2);
        sheet.addMergedRegion(new CellRangeAddress(5,6,3,4));
        sheet.addMergedRegion(new CellRangeAddress(5,6,7,17));
        sheet.addMergedRegion(new CellRangeAddress(5,6,18,24));
        sheet.addMergedRegion(new CellRangeAddress(5,8,25,35));
        sheet.addMergedRegion(new CellRangeAddress(5,8,36,46));
        sheet.addMergedRegion(new CellRangeAddress(5,8,0,2));
        sheet.addMergedRegion(new CellRangeAddress(5,8,5,6));
       
     
        sheet.addMergedRegion(new CellRangeAddress(7,8,3,4));
        sheet.addMergedRegion(new CellRangeAddress(7,8,7,17));
        sheet.addMergedRegion(new CellRangeAddress(7,8,18,24));
        sheet.addMergedRegion(new CellRangeAddress(9,9,0,42));
        sheet.addMergedRegion(new CellRangeAddress(10,10,10,18));
  		sheet.addMergedRegion(new CellRangeAddress(11,11,10,18));
   
  		


 
        sheet.addMergedRegion(new CellRangeAddress(12,12,24,45));
        sheet.addMergedRegion(new CellRangeAddress(13,13,24,45));
        
        

        int m=14;
  		for (m = 14; m <=38 ; m++) {
  		  sheet.addMergedRegion(new CellRangeAddress(m,m,20,21));
  		sheet.addMergedRegion(new CellRangeAddress(m,m,22,23));
  		}
  		
  		int o=24;
  		for (o = 24; o <=46 ; o++) {
  		  sheet.addMergedRegion(new CellRangeAddress(39,43,o,o));

  		}
        

  		
        sheet.addMergedRegion(new CellRangeAddress(39,43,0,23));
        sheet.addMergedRegion(new CellRangeAddress(44,49,0,2));
        sheet.addMergedRegion(new CellRangeAddress(44,49,6,7));
        sheet.addMergedRegion(new CellRangeAddress(44,45,3,5));
        sheet.addMergedRegion(new CellRangeAddress(46,47,3,5));
        sheet.addMergedRegion(new CellRangeAddress(48,49,3,5));    
        sheet.addMergedRegion(new CellRangeAddress(44,45,8,21));
        sheet.addMergedRegion(new CellRangeAddress(46,47,8,21));
        sheet.addMergedRegion(new CellRangeAddress(48,49,8,21));
        sheet.addMergedRegion(new CellRangeAddress(44,49,22,29));  
        sheet.addMergedRegion(new CellRangeAddress(44,45,30,46));
        sheet.addMergedRegion(new CellRangeAddress(46,47,30,46));
        sheet.addMergedRegion(new CellRangeAddress(48,49,30,46));
        sheet.addMergedRegion(new CellRangeAddress(10,13,8,8));
        sheet.addMergedRegion(new CellRangeAddress(10,13,9,9));
        sheet.addMergedRegion(new CellRangeAddress(12,13,15,15));
        sheet.addMergedRegion(new CellRangeAddress(12,13,16,16));
        sheet.addMergedRegion(new CellRangeAddress(12,13,17,17));
        sheet.addMergedRegion(new CellRangeAddress(12,13,18,18));
        sheet.addMergedRegion(new CellRangeAddress(10,13,19,19));
        sheet.addMergedRegion(new CellRangeAddress(10,13,20,20));
        sheet.addMergedRegion(new CellRangeAddress(10,13,21,21));
        sheet.addMergedRegion(new CellRangeAddress(10,13,22,22));
        sheet.addMergedRegion(new CellRangeAddress(10,13,23,23));
        sheet.addMergedRegion(new CellRangeAddress(10,13,46,46));

        
        row=sheet.createRow(7);
        createCell(row,3,"RACIONES ATENDIDAS ALMUERZOS:",style2);
        createCell(row,7,"RACIONES ATENDIDAS C.A:AM______ PM______",style2);
        createCell(row,18,"COMIDA:",style2);

        row=sheet.createRow(10);
      int k=0;
		for (k = 20; k <=41 ; k++) {
			 createCell(row,k,"",style11);
		}
        createCell(row,0,"No",style3);
        createCell(row,1,"TIPO DOCUMENTO",style3);
        createCell(row,2,"No. DOCUMENTO DE",style3);
        createCell(row,3,"PRIMER APELLIDO TITULAR",style3);
        createCell(row,4,"SEGUNDO APELLIDO TITULAR",style3);
        createCell(row,5,"PRIMER NOMBRE TITULAR",style3);
        createCell(row,6,"SEGUNDO NOMBRE TITULAR",style3);
        createCell(row,7,"EDAD/FECHA DE",style3);
	    createCell(row,8,"GENERO",style4);
        createCell(row,9,"GRADO EDUCATIVO",style4);
        createCell(row,10,"tipo de",style);
        createCell(row,15,"",style);
        createCell(row,16,"",style);
        createCell(row,17,"",style);
        createCell(row,18,"",style);
        createCell(row,19,"PERTENENCIA ÉTNICA",style4);
        createCell(row,20,"VICTIMAS DEL",style8);
        createCell(row,21,"CONFLICTO",style7);
        createCell(row,22,"EN CONDICION DE",style8);
        createCell(row,23,"DISCAPACIDAD",style7);
        createCell(row,24,"FECHA DE ENTREGA. Escriba el dia hábil al cual corresponde la entrega del complemento alimentairo",style3);
        createCell(row,46,"RACIONES ATENDIDAS",style4);
        sheet.addMergedRegion(new CellRangeAddress(10,10,24,45));
  
        
        
        row=sheet.createRow(11);
        
        int w=0;
		for (w = 20; w <=41 ; w++) {
			 createCell(row,w,"",style11);
		}
        createCell(row,0,"",style3);
        createCell(row,1,"",style3);
        createCell(row,2,"IDENTIDAD",style3);
        createCell(row,3,"DE DERECHO",style3);
        createCell(row,4,"DE DERECHO",style3);
        createCell(row,5,"DE DERECHO",style3);
        createCell(row,6,"DE DERECHO",style3);
        createCell(row,7,"NACIMIENTO",style3);
        createCell(row,8,"",style4);
        createCell(row,9,"",style4);
        createCell(row,10,"complemento",style);
        createCell(row,11,"",style);
        createCell(row,12,"",style);
        createCell(row,13,"",style);
        createCell(row,15,"",style);
        createCell(row,16,"",style);
        createCell(row,17,"",style);
        createCell(row,18,"",style);
        createCell(row,19,"",style4);
        createCell(row,20,"",style8);
        createCell(row,21,"",style7);
        createCell(row,22,"",style8);
        createCell(row,23,"",style7);
        createCell(row,46,"",style4);
      
        
        row=sheet.createRow(12);
      int d=0;
		for (d = 0; d <=7 ; d++) {
			 createCell(row,d,"",style3);
		}
	  createCell(row,8,"",style4);
      createCell(row,9,"",style4);
      createCell(row,10,"",style4);
      createCell(row,11,"",style4);
      createCell(row,12,"",style4);
      createCell(row,13,"",style4);
      createCell(row,14,"",style4);
      createCell(row,15,"RACION CASA",style4);
      createCell(row,16,"R.T CALIENTE",style4);
      createCell(row,17,"INDUSTRIALIZADA",style4);
      createCell(row,18,"BONO",style4);
      createCell(row,19,"",style7);
      createCell(row,20,"",style8);
      createCell(row,21,"",style7);
      createCell(row,22,"",style8);
      createCell(row,23,"",style7);
      createCell(row,24,"Días de atencion. Marque con una equis(x) el día que el titular",style3);
      createCell(row,39,"",style10);
      createCell(row,42,"",style10);
      createCell(row,46,"",style4);

        
        
        
        row=sheet.createRow(13);
        int e=0;
		for (e = 0; e <=7 ; e++) {
			 createCell(row,e,"",style3);
		}
		createCell(row,8,"",style4);
        createCell(row,9,"",style4);
        createCell(row,10,"ALMUERZO",style4);
        createCell(row,11,"AM",style4);
        createCell(row,12,"PM",style4);
        createCell(row,13,"DESAYUNO",style4);
        createCell(row,14,"COMIDA",style4);
        createCell(row,15,"",style4);
        createCell(row,16,"",style4);
        createCell(row,17,"",style4);
        createCell(row,18,"",style4);
        createCell(row,19,"",style7);
        createCell(row,20,"",style8);
        createCell(row,21,"",style7);
        createCell(row,22,"",style8);
        createCell(row,23,"",style7);
        createCell(row,24,"de derecho recibe el complemento alimentario",style3);
        createCell(row,39,"",style10);
        createCell(row,42,"",style10);
        createCell(row,46,"",style4);

        
       
//			
			
			
	  		  sheet.addMergedRegion(new CellRangeAddress(0,49,47,1000));
	  		sheet.addMergedRegion(new CellRangeAddress(50,1000,0,1000));
	  		  
	


		
		int x=15;
		for ( x= 15; x <=63 ; x++) {
			 row=sheet.createRow(x);
			 
			 int s=0;
				for ( s= 0; s <=63 ; s++) {
					 createCell(row,s,"",style11);
				}
		}
		
        
        row=sheet.createRow(39);
        createCell(row,0,"FIRMA DIARIA REPRESENTANTE SEDE EDUCATIVA",style2);
        
        row=sheet.createRow(44);
        createCell(row,0,"RESPONSABLE SEDE EDUCATIVA:",style2);
        createCell(row,3,"NOMBRE:",style2);
        createCell(row,30,"NOMBRE:",style2);
        createCell(row,6,"RECTOR INSTITUCION EDUCATIVA:",style2);
        createCell(row,22,"RESPONSABLE DEL OPERADOR:",style2);
       
        row=sheet.createRow(46);
        createCell(row,3,"FIRMA:",style2);
        createCell(row,30,"FIRMA:",style2);
        
        
        row=sheet.createRow(48);
        createCell(row,3,"CEDULA:",style2);
        createCell(row,30,"CEDULA:",style2);
		

	}
//	private void writeDataLines() {
//		int rowCount=14;
//		
//		CellStyle style=workbook.createCellStyle();
//		XSSFFont font=workbook.createFont();
//		font.setFontHeight(12);
//		style.setFont(font);
//		
//		for(Estudiante stu:listStudent) {
//			Row row=sheet.createRow(rowCount++);
//			int columnCount=1;
//		
//			createCell(row, columnCount++, stu.getUnTipoDocumento().getNombre(), style);
//			createCell(row, columnCount++, stu.getNumeroDocumento(), style);
//			createCell(row, columnCount++, stu.getApellido1(), style);
//			createCell(row, columnCount++, stu.getApellido2(), style);
//			createCell(row, columnCount++, stu.getNombre1(), style);
//			createCell(row, columnCount++, stu.getNombre2(), style);
//			createCell(row, columnCount++, stu.getFechaNacimiento(), style);
//			createCell(row, columnCount++, stu.getGenero(), style);
//			createCell(row, columnCount++, stu.getGrado(), style);
//		}
//	}
	
	public void export(HttpServletResponse response) throws IOException{
		writeHeaderLine();
//		writeDataLines();
	
		
		ServletOutputStream outputStream=response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	
}
