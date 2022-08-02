package com.SGA.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private Long id;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
