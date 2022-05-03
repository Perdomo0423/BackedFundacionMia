package com.SGA.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class UsuarioDto {

    @NotNull
    private Long id;
    @NotBlank(message = "La contraseña no debe estar en blanco")
    private String currentPassword;

    @NotBlank(message="La nueva contraseña no debe estar en blanco")
    private String newPassword;

    @NotBlank(message="Confirm Password must not be blank")
    private String confirmPassword;

    // username
    // email

    public UsuarioDto(){}
    public UsuarioDto(Long id) {this.id = id;}

}
