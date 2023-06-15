package com.emprende.portal.comercio.Repository;

import com.emprende.portal.comercio.Models.UsuariosModel;
import com.emprende.portal.comercio.dto.responseDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuariosRepository extends JpaRepository<UsuariosModel, Long> {
    responseDto findByCorreo(String correo);
}
