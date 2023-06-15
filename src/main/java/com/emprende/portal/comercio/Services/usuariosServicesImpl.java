package com.emprende.portal.comercio.Services;

import com.emprende.portal.comercio.Models.UsuariosModel;
import com.emprende.portal.comercio.Repository.usuariosRepository;
import com.emprende.portal.comercio.dto.responseDto;
import com.emprende.portal.comercio.dto.responseDtoEnum;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class usuariosServicesImpl implements usuariosServices {
    responseDto rsp = new responseDto();
    @Autowired
    usuariosRepository userRepo;
    @Override
    public responseDto getAllUsuarios() {
        try{
            List<UsuariosModel> usuariosList = userRepo.findAll();
            rsp.setResponse("Usuarios encontrados.", usuariosList, responseDtoEnum.OK);
        }catch (Exception exp){
            rsp.setResponse("No se ha podido devolver la lista usuarios", exp.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto getUsuariosId(Long id) {
        try{
            Optional<UsuariosModel> usuarioOptional = userRepo.findById(id);
            if(usuarioOptional.isPresent()){
                UsuariosModel usuario = usuarioOptional.get();
                rsp.setResponse("Usuario encontrado", usuario, responseDtoEnum.OK);
            }else{
                rsp.setResponse(String.format("[INFO]: El usuario no ha sido encontrado.\n el \'id\' utilizado es %d", id),
                        responseDtoEnum.NO_CONTENT);
            }
        }catch(Exception exp){
            rsp.setResponse("Error al buscar el usuario",exp.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto updateUsuarios(UsuariosModel user, Long id) {
        try{
            Optional<UsuariosModel> usuarioOptional = userRepo.findById(id);
            if (usuarioOptional.isPresent()) {
                UsuariosModel usuario = usuarioOptional.get();
                usuario.setActivo(user.getActivo());
                usuario.setApellidos(user.getApellidos());
                usuario.setContacto(user.getContacto());
                usuario.setCreated(user.getCreated());
                usuario.setUsername(user.getUsername());
                usuario.setDireccion(user.getDireccion());
                usuario.setNombres(user.getNombres());
                usuario.setIdComercio(user.getIdComercio());
                usuario.setPassword(user.getPassword());
                usuario.setRol(user.getRol());
                rsp.setResponse("Usuario actualizado correctamente.",usuario, responseDtoEnum.OK);
                userRepo.save(usuario);
            } else {
                rsp.setResponse("Usuario no ha sido encontrado", responseDtoEnum.NO_CONTENT);
            }
        }catch (Exception exp){
            rsp.setResponse("No se a podido actualizar el usuario",exp.getMessage(),responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto saveUsuarios(UsuariosModel user) {
        try {
            rsp.setResponse("Usuario guardado correctamente",user,responseDtoEnum.OK);
            userRepo.save(user);
        } catch (Exception e) {
            rsp.setResponse("Error al guardar el usuario", e.getMessage(),responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto deleteUsuarios(UsuariosModel user) {
        try {
            UsuariosModel uTmp = new UsuariosModel();
            rsp.setResponse("El usuario ha sido eliminado con exito.",uTmp,responseDtoEnum.OK);
            userRepo.delete(user);
        } catch (Exception e) {
            rsp.setResponse("Error al eliminar el usuario", e.getMessage(), responseDtoEnum.INTERNAL_SERVER_ERROR);
        }
        return rsp;
    }

    @Override
    public responseDto findByUsername(String username) {
        UsuariosModel userM = userRepo.findAll()
                .stream()
                .filter(e -> e.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        //userM.setPassword(new BCryptPasswordEncoder().encode(userM.getPassword()));
        return new responseDto("Usuario Encontrado", userM, responseDtoEnum.OK);
    }

    public responseDto cambiarContrasena(String username, String nuevaContrasena) {
        UsuariosModel user = (UsuariosModel) userRepo.findByUsername(username).getRespuesta();

        if (user != null) {
            user.setPassword(nuevaContrasena);
            userRepo.save(user);
            rsp.setResponse("Contraseña cambiada",user, responseDtoEnum.OK);
        }else{
            rsp.setResponse("Contrseña no cambiada",responseDtoEnum.BAD_REQUEST);
        }
        return rsp;
    }
}
