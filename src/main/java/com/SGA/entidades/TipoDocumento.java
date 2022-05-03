package com.SGA.entidades;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="tipo_documento")
public class TipoDocumento {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="idTipoDocumento")
    private  int idTipoDocumento;
    @Column(name="tipCodigo")
    private Long codigo;
    @Column(name="tipNombre")
    private String nombre;

    public TipoDocumento(String nombre) {
        this.nombre = nombre;
    }

    public TipoDocumento() {
        super();
    }



    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public TipoDocumento(Long codigo, String nombre) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
    }

}