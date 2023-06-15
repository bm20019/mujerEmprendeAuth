package com.emprende.portal.comercio.Services;

import com.emprende.portal.comercio.Models.UsuariosModel;
import com.emprende.portal.comercio.dto.responseDto;

public interface usuariosServices {
    public responseDto getAllUsuarios();
    public responseDto getUsuariosId(Long id);
    public responseDto updateUsuarios(UsuariosModel user, Long id);
    public responseDto saveUsuarios(UsuariosModel user);
    public responseDto deleteUsuarios(UsuariosModel user);
    public responseDto findByUsername(String username);
    public responseDto cambiarContrasena(String username, String nuevaContrasena);
}
