package com.emprende.portal.comercio.Models;

import lombok.Getter;
import lombok.Setter;

public class TokenInfo {
    public TokenInfo(String jwtToken){
        this.jwtToken = jwtToken;
    }
    @Getter
    @Setter
    private String jwtToken;
}
