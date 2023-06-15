package com.emprende.portal.comercio.Models;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "usuarios", catalog = "", schema = "comercios")
public class UsuariosModel implements Serializable{

    private static final long serialVersionUID = 1L;

    public UsuariosModel() {
    }
    @Id
    @Getter
    @Setter
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "correo")
    private String username;

    @Getter
    @Setter
    @Column(name = "nombres")
    private String nombres;

    @Getter
    @Setter
    @Column(name = "apellidos")
    private String apellidos;

    @Getter
    @Setter
    @Column(name = "contacto")
    private String contacto;

    @Getter
    @Setter
    @Column(name = "direccion")
    private String direccion;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "activo")
    private Long activo;

    @Getter
    @Setter
    @Column(name = "rol")
    private String rol;

    @Getter
    @Setter
    @Column(name = "created")
    private LocalDate created;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comercio", referencedColumnName = "id_comercio")
    private ComerciosModel idComercio;

}