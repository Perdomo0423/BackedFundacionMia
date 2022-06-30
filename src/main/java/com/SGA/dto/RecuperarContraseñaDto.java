package com.SGA.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecuperarContraseñaDto {
	// private Long id;
    private String username;
	private String email;
	private String password;
}