package com.emprende.portal.comercio.Controllers;

import com.emprende.portal.comercio.Models.TokenInfo;
import com.emprende.portal.comercio.Models.UsuarioAuthModel;
import com.emprende.portal.comercio.Services.Security.jwtUtilService;
import com.emprende.portal.comercio.Util.Utils;
import com.emprende.portal.comercio.dto.responseDto;
import com.emprende.portal.comercio.dto.responseDtoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comercio/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class authController {
    @Autowired
    private jwtUtilService jwtUService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService usuarioDetailsService;
    @RequestMapping(method = RequestMethod.POST)
    public responseDto authUser(@RequestBody UsuarioAuthModel authenticationModel){
        System.err.println(authenticationModel.getCorreo());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationModel.getCorreo(), Utils.encriptarAMD5(authenticationModel.getClave())
                ));

        System.err.println(String.format("U: %s | P: %s", authenticationModel.getCorreo(),authenticationModel.getClave()));
        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationModel.getCorreo());

        final String jwt = jwtUService.generateToken(userDetails);

        return new responseDto("Token Generado",new TokenInfo(jwt), responseDtoEnum.OK);
    }
}
