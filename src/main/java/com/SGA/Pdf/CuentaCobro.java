package com.SGA.Pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.poi.ss.usermodel.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Estudiante;
import com.SGA.repositorio.ContratistaRepository;
import com.SGA.repositorio.TipoDocumentoRepository;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CuentaCobro {


	private static Logger logger = LoggerFactory.getLogger(CuentaCobro.class);

		
	

	public static ByteArrayInputStream  customerPDFReport(List<Contratista>customers) throws MalformedURLException, IOException {

		//rotar la pagina
		Document  document = new Document(PageSize.A4.rotate());
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		
		try {
			 String timeStamp = new SimpleDateFormat("dd/MM/yyyy ").format(Calendar.getInstance().getTime());

			
			PdfWriter.getInstance(document, out);
			document.open();
			
			
			Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
			Paragraph titulo = new Paragraph("REPRESENTACIÓN GRAFICA", fontTitle);
			titulo.setAlignment(Element.ALIGN_CENTER);
			document.add(titulo);		
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
	
			
			Font fuentee = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
			Paragraph documento = new Paragraph("Datos del Documento", fuentee);
			document.add(documento);	
			
			PdfPTable tablaDocumento = new PdfPTable(2);
			
			//tamaño a las celdas
			float[] medidasCeldasDocumento = {5.80f, 3.70f};
			tablaDocumento.setWidths(medidasCeldasDocumento);
			
			for	(Contratista datosDocumento : customers){
				
				PdfPCell fechaEmiCell = new PdfPCell(new Phrase(String.valueOf("Fecha de Emisión: "+timeStamp)));
				PdfPCell fechaVenCell = new PdfPCell(new Phrase(String.valueOf("fecha Vencimiento: ")));
				PdfPCell formaPagoCell = new PdfPCell(new Phrase(String.valueOf("Forma de Pago: ")));
				PdfPCell medioPagoCell = new PdfPCell(new Phrase(String.valueOf("Medio de Pago: "+datosDocumento.getNit())));
				
				
				//para que las celdas no tengan bordes
				fechaEmiCell.setBorder(0);
				fechaVenCell.setBorder(0);
				formaPagoCell.setBorder(0);
				medioPagoCell.setBorder(0);

				
				//para incluir los datos al pdf
				tablaDocumento.addCell(fechaEmiCell);	
				tablaDocumento.addCell(fechaVenCell);	
				tablaDocumento.addCell(formaPagoCell);
				tablaDocumento.addCell(medioPagoCell);
				
				};
				
				document.add(tablaDocumento);
				document.add(Chunk.NEWLINE);
			

				
			//esribir datos del emisor / vendedor
			
			Font fontt = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
			Paragraph datos = new Paragraph("Datos del emisor / Vendedor", fontt);
			document.add(datos);		
			PdfPTable tablaEmisor = new PdfPTable(2);
			
			//tamaño a las celdas
			float[] medidasCeldas = {5.80f, 3.70f};
			tablaEmisor.setWidths(medidasCeldas);

			// imprimir los datos en dos columnas
		for	(Contratista contratista : customers){
			
			PdfPCell razonCell = new PdfPCell(new Phrase(String.valueOf("Razón Social: "+contratista.getIdPersona().getNombre())));
			PdfPCell paisCell = new PdfPCell(new Phrase(String.valueOf("País: "+contratista.getIdPersona().getLugarDeNacimiento().getUnDepartamento().getUnPais().getNombre())));
			PdfPCell DepartamentoCell = new PdfPCell(new Phrase(String.valueOf("Departamento: "+contratista.getIdPersona().getLugarDeNacimiento().getUnDepartamento().getNombre())));
			PdfPCell nitCell = new PdfPCell(new Phrase(String.valueOf("Municipio/Ciudad: "+contratista.getIdPersona().getLugarDeNacimiento().getNombre())));
			PdfPCell nombreCell = new PdfPCell(new Phrase(String.valueOf("Nombre Comercial: "+contratista.getNombreZona())));
			PdfPCell municipioCell = new PdfPCell(new Phrase(String.valueOf("Nit del Emisor: "+contratista.getNit())));
			
			//para que las celdas no tengan bordes
			razonCell.setBorder(0);
			paisCell.setBorder(0);
			DepartamentoCell.setBorder(0);
			municipioCell.setBorder(0);
			nombreCell.setBorder(0);
			nitCell.setBorder(0);
			
			//para incluir los datos al pdf
			tablaEmisor.addCell(razonCell);	
			tablaEmisor.addCell(paisCell);	
			tablaEmisor.addCell(nombreCell);
			tablaEmisor.addCell(DepartamentoCell);
			tablaEmisor.addCell(municipioCell);
			tablaEmisor.addCell(nitCell);	

			
			};
			//para que se muestren los datos en el pdf
			document.add(tablaEmisor);
			document.add(Chunk.NEWLINE);
		
			
			//
			
			Font fuent = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
			Paragraph adquiriente = new Paragraph("Datos del Adquiriente/Comprador", fuent);
			document.add(adquiriente);		
			PdfPTable tablaAdquiriente = new PdfPTable(2);
			
			//tamaño a las celdas
			float[] medidasCeldasAdquiriente = {5.80f, 3.70f};
			tablaAdquiriente.setWidths(medidasCeldasAdquiriente);
			
			for	(Contratista datosAdquiriente : customers){
				
				PdfPCell fechaEmiCell = new PdfPCell(new Phrase(String.valueOf("Fecha de Emisión: "+"aaaaaaaaaaaaa")));
				PdfPCell fechaVenCell = new PdfPCell(new Phrase(String.valueOf("fecha Vencimiento: "+"aaaaaaaaaaaaa")));
				PdfPCell formaPagoCell = new PdfPCell(new Phrase(String.valueOf("Forma de Pago: "+"aaaaaaaaaaaaa")));
				PdfPCell medioPagoCell = new PdfPCell(new Phrase(String.valueOf("Medio de Pago: "+datosAdquiriente.getNit())));
				
				
				//para que las celdas no tengan bordes
				fechaEmiCell.setBorder(0);
				fechaVenCell.setBorder(0);
				formaPagoCell.setBorder(0);
				medioPagoCell.setBorder(0);

				
				//para incluir los datos al pdf
				tablaAdquiriente.addCell(fechaEmiCell);	
				tablaAdquiriente.addCell(fechaVenCell);	
				tablaAdquiriente.addCell(formaPagoCell);
				tablaAdquiriente.addCell(medioPagoCell);
				
				};
				
				document.add(tablaAdquiriente);
				
				int i=0;
				for(i=1;i<=10;i++) {
					document.add(Chunk.NEWLINE);
					
				}
				
				


			
			
			
			
			
			
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
			Paragraph para = new Paragraph("Detalles de Productos", font);
			document.add(para);		
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(12);
			 
			float[] medidaCeldas = {0.70f, 1.00f, 3.40f, 1.40f, 1.40f, 1.40f, 1.40f, 1.00f, 0.60f, 1.00f, 0.60f, 1.70f};
			table.setWidths(medidaCeldas);
			

			Stream.of("No.","Código","Descripion","Cantidad","Precio unitario", "Descuento detalle","Recargo detalle","IVA","%","INC","%","Precio unitario de venta").forEach(headerTitle ->{
				PdfPCell header = new PdfPCell();
				Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,10);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				para.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
				header.setPhrase(new Phrase(headerTitle, headerFont));
				table.addCell(header);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);	
			});;
			
			
			int con=0;
			
			for (Contratista customer : customers) {
				con = con+1;
				
				PdfPCell numeroCell = new PdfPCell(new Phrase(String.valueOf(con)));
				numeroCell.setPaddingLeft(4);
				numeroCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				numeroCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(numeroCell);	
				
				PdfPCell codigoCell = new PdfPCell(new Phrase(String.valueOf("1")));
				codigoCell.setPaddingLeft(4);
				codigoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				codigoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(codigoCell);	
				
				PdfPCell descripcionCell = new PdfPCell(new Phrase(String.valueOf("PRESTACIÓN DEL SERVICIO DE ALIMENTACIÓN ESCOLAR— PAE JORNADA MAÑANA- TARDE Y ALMUERZO EN LA MODALIDAD DE PREPARACIÓN EN SITIO, PARA LOS NIÑOS, NIÑAS Y ADOLESCENTES REGISTRADOS EN EL SIMAT DE LAS INSTITUCIONES EDUCATIVAS OFICIALES DE LOS 35 MUNICIPIOS NO CERTIFICADOS DEL DEPARTAMENTO DEL HUILA -"+customer.getIdZona().getNombre_zona()+".")));
				descripcionCell.setPaddingLeft(4);
				descripcionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				descripcionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(descripcionCell);			
			
				PdfPCell cantidadCell = new PdfPCell(new Phrase(String.valueOf(customer.getCantidadAlmuerzo())));
				cantidadCell.setPaddingLeft(4);
				cantidadCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cantidadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cantidadCell);	
				
				PdfPCell costoCell = new PdfPCell(new Phrase(String.valueOf(customer.getCostoAlmuerzo())));
				costoCell.setPaddingLeft(4);
				costoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				costoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(costoCell);
				
				PdfPCell descuentoCell = new PdfPCell(new Phrase(String.valueOf(" ")));
				descuentoCell.setPaddingLeft(4);
				descuentoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				descuentoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(descuentoCell);
				
				PdfPCell recargoCell = new PdfPCell(new Phrase(String.valueOf(" ")));
				recargoCell.setPaddingLeft(4);
				recargoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				recargoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(recargoCell);
				
				PdfPCell ivaCell = new PdfPCell(new Phrase(String.valueOf(" ")));
				ivaCell.setPaddingLeft(4);
				ivaCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ivaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(ivaCell);
				
				PdfPCell porcentajeCell = new PdfPCell(new Phrase(String.valueOf(" ")));
				porcentajeCell.setPaddingLeft(4);
				porcentajeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(porcentajeCell);
				
				PdfPCell incCell = new PdfPCell(new Phrase(String.valueOf(" ")));
				incCell.setPaddingLeft(4);
				incCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				incCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(incCell);
				
				PdfPCell porcentaje2Cell = new PdfPCell(new Phrase(String.valueOf(" ")));
				porcentaje2Cell.setPaddingLeft(4);
				porcentaje2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(porcentaje2Cell);
				
				PdfPCell totalCell = new PdfPCell(new Phrase(String.valueOf(customer.getCantidadAlmuerzo()*customer.getCostoAlmuerzo())));
				totalCell.setPaddingLeft(4);
				totalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(totalCell);
							
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