package com.mauramirez.adeaproject.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    public static final long serialVersionUID = 1L;
    @Id
    @Column(name = "login")
    @NotEmpty
    private String login;
    @Column(name = "password")
    @NotEmpty
    private String password;
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;
    @Column(name = "cliente")
    @NotNull
    private Float cliente;
    @Column(name = "email")
    @NotEmpty
    @Email
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_baja")
    private Date fechaBaja;
    @Column(name = "status")
    private String status;
    @Column(name = "intentos")
    private Float intentos;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_revocado")
    private Date fechaRevocado;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_vigencia")
    @NotNull
    private Date fechaVigencia;
    @Column(name = "no_acceso")
    private Integer noAcceso;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "area")
    private Long area;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

}
