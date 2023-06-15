package com.emprende.portal.comercio.Services.Security;

import com.emprende.portal.comercio.Models.UsuariosModel;
import com.emprende.portal.comercio.Repository.usuariosRepository;
import com.emprende.portal.comercio.dto.responseDtoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.emprende.portal.comercio.dto.responseDto;
@Service
public class userDetailsServiceImpl implements UserDetailsService {
    @Autowired
    usuariosRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user  = getbyEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        UsuariosModel usr =(UsuariosModel) user.getRespuesta();

        System.err.println(String.format("Username: %s | Password: %s", email, usr.getPassword()));
        return User
                .withUsername(email)
                .password(usr.getPassword())
                .roles(usr.getRol())
                .build();
    }

    public responseDto getbyEmail(String correo){
        UsuariosModel userM = userRepo.findAll()
                .stream()
                .filter(e -> e.getCorreo().equals(correo))
                .findFirst()
                .orElse(null);
        userM.setPassword(new BCryptPasswordEncoder().encode(userM.getPassword()));
        return new responseDto("Usuario Encontrado", userM, responseDtoEnum.OK);
    }
}
