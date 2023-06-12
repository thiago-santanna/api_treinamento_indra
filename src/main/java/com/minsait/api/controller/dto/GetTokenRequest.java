package com.minsait.api.controller.dto;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class GetTokenRequest {
    private String userName;
    private String password;

    public Boolean verificaSenha(String senhaLogin, String senhaUsuario) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaLogin, senhaUsuario);
    }
}
