package com.emprende.portal.comercio.Models.Security;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class UsuarioAuthModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String correo;

    @Getter
    @Setter
    private String clave;

    public UsuarioAuthModel(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }
}
