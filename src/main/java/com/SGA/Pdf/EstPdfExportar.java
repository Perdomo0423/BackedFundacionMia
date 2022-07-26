    package com.SGA.Pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.Phaser;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.SGA.entidades.*;







public class EstPdfExportar {
	private static Logger logger = LoggerFactory.getLogger(EstPdfExportar.class);
	
	public static ByteArrayInputStream  customerPDFReport(List<Estudiante>customers) throws MalformedURLException, IOException {
		
		Document  document = new Document(PageSize.A4.rotate());
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			//añadir texto al archivo pdf

			Image imagen = Image.getInstance("mia.png");  
			imagen.scaleAbsoluteWidth(180f);
			imagen.scaleAbsoluteHeight(150f);
			imagen.setAbsolutePosition(15f, 430f);
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
			Paragraph para = new Paragraph("REPORTES FUNDACION MIA", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);		
			document.add(imagen);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
		
			PdfPTable table = new PdfPTable(9);
			 
			float[] medidaCeldas = {1.90f, 1.70f, 2.20f, 1.90f,3.25f, 1.70f, 1.70f, 1.90f, 2.40f};
			table.setWidths(medidaCeldas);
			
			//añadir tabla al pdf 
			Stream.of("MUNICIPIO","SEDE","INSTITTUTO","JORNADA","T.ALIMENTACION","T.ETNIA","NOMBRE","APELLIDO","GENERO").forEach(headerTitle ->{
				PdfPCell header = new PdfPCell();
				Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,10);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				para.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
				header.setBorder(0);
				header.setPhrase(new Phrase(headerTitle, headerFont));
				table.addCell(header);
				
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				
			});;
			
			for (Estudiante customer : customers) {
			
//				PdfPCell municipioCell = new PdfPCell(new Phrase(String.valueOf(customer.getMunicipioRecidencia())));
//				municipioCell.setPaddingLeft(4);
//				municipioCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				municipioCell.setBorder(0);
//				municipioCell.setPaddingBottom(0);
//				table.addCell(municipioCell);	
				
//				PdfPCell sedeCell = new PdfPCell(new Phrase(String.valueOf(customer.getUnaSede().getNombre())));
//				sedeCell.setPaddingLeft(4);
////				municipioCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////				municipioCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				sedeCell.setBorder(0);
//				table.addCell(sedeCell);
//				
//				PdfPCell institucionCell = new PdfPCell(new Phrase(String.valueOf(customer.getUnaInstitucion().getNombre())));
//				institucionCell.setPaddingLeft(4);
//				sedeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				sedeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				institucionCell.setBorder(0);
//				table.addCell(institucionCell);
				
//				PdfPCell jornadaCell = new PdfPCell(new Phrase(String.valueOf(customer.getUnaJornada().getNombre())));
//				jornadaCell.setPaddingLeft(4);
//				jornadaCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				jornadaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				jornadaCell.setBorder(0);
//				table.addCell(jornadaCell);
				
//				PdfPCell tipoAlimentacionCell = new PdfPCell(new Phrase(String.valueOf(customer.getIdBeneficio().getNombre())));
//				tipoAlimentacionCell.setPaddingLeft(4);
//				tipoAlimentacionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				tipoAlimentacionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tipoAlimentacionCell.setBorder(0);
//				table.addCell(tipoAlimentacionCell);
				
//				PdfPCell etniaCell = new PdfPCell(new Phrase(String.valueOf(customer.getUnaEtnia().getNombre())));
//				etniaCell.setPaddingLeft(4);
//				etniaCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				etniaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				etniaCell.setBorder(0);
//				table.addCell(etniaCell);
				
				
				PdfPCell nombreCell = new PdfPCell(new Phrase(String.valueOf(customer.getNombre1())));
				nombreCell.setPaddingLeft(4);
				nombreCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nombreCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				nombreCell.setBorder(0);
				table.addCell(nombreCell);
				
				PdfPCell apellidoCell = new PdfPCell(new Phrase(String.valueOf(customer.getApellido1())));
				apellidoCell.setPaddingLeft(4);
				apellidoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				apellidoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				apellidoCell.setBorder(0);
				table.addCell(apellidoCell);
				
				PdfPCell generoCell = new PdfPCell(new Phrase(String.valueOf(customer.getGenero())));
				generoCell.setPaddingLeft(4);
				generoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				generoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				generoCell.setBorder(0);
				table.addCell(generoCell);
	
//				
//				PdfPCell zonaCell = new PdfPCell(new Phrase(String.valueOf(customer.get)));
//				zonaCell.setPaddingLeft(4);
//				zonaCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				zonaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				zonaCell.setBorder(0);
//				table.addCell(zonaCell);
//				
			}
			document.add(table);
			document.setPageSize(PageSize.A4);
			document.close();
			
		} catch (DocumentException e) {
			logger.error(e.toString());
		}
		
		return new ByteArrayInputStream(out.toByteArray());
	}


}
