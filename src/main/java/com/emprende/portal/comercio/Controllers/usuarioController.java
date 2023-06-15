package com.emprende.portal.comercio.Controllers;

import com.emprende.portal.comercio.Models.UsuariosModel;
import com.emprende.portal.comercio.Services.usuariosServices;
import com.emprende.portal.comercio.dto.responseDto;
import com.emprende.portal.comercio.dto.responseDtoEnum;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comercio/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class usuarioController {
    @Autowired
    usuariosServices usuariosSrvc;
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public responseDto saveUsuario(@RequestBody UsuariosModel usuario){
        return usuariosSrvc.saveUsuarios(usuario);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{id}")
    public responseDto getUsuarioId(@PathVariable Long id){
        return usuariosSrvc.getUsuariosId(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public responseDto getAllUsuarios(){
        return usuariosSrvc.getAllUsuarios();
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/update/{id}")
    public responseDto updateUsuario(@RequestBody UsuariosModel usuario, @PathVariable Long id){
        return usuariosSrvc.updateUsuarios(usuario, id);
    }
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete")
    public responseDto deleteUsuario(@RequestBody UsuariosModel usuario){
        UsuariosModel userTmp =(UsuariosModel)usuariosSrvc.getUsuariosId(usuario.getId()).getRespuesta();
        return usuariosSrvc.deleteUsuarios(userTmp);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public responseDto deleteUsuarioId(@PathVariable Long id){
        UsuariosModel userTmp =(UsuariosModel)usuariosSrvc.getUsuariosId(id).getRespuesta();
        return usuariosSrvc.deleteUsuarios(userTmp);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{username}/contrasena")
    public responseDto cambiarContrasena(@PathVariable String username, @RequestBody String nuevaContrasena) {
        if (usuariosSrvc.cambiarContrasena(username, nuevaContrasena).getCodigo() == 200 ) {
            return new responseDto("Contraseña cambiada exitosamente",username, responseDtoEnum.OK);
        } else {
            return new responseDto("Contraseña no cambiada",responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
