package com.SGA.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayInputStream;

@Getter
@Setter
public class JasperReportes {

   private String fileName;
   private ByteArrayInputStream stream;
   private int length;



}
