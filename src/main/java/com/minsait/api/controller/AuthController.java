package com.minsait.api.controller;

import com.minsait.api.controller.dto.GetTokenRequest;
import com.minsait.api.controller.dto.GetTokenResponse;
import com.minsait.api.repository.UsuarioEntity;
import com.minsait.api.repository.UsuarioRepository;
import com.minsait.api.sicurity.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final String SEPARADOR_PERMISSOES = ",";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/get-token")
    public ResponseEntity<GetTokenResponse> getToken(@RequestBody GetTokenRequest request){

        UsuarioEntity login = usuarioRepository.findByLogin(request.getUserName());
        if (login == null){
            return new ResponseEntity<>(GetTokenResponse.builder().build(), HttpStatus.UNAUTHORIZED);
        }
        if(login.getSenha() == null || login.getLogin() == null){
            return new ResponseEntity<>(GetTokenResponse.builder().build(), HttpStatus.UNAUTHORIZED);
        }

        if(request.verificaSenha(request.getPassword(), login.getSenha())){
            final ArrayList<String> permissions = new ArrayList<>();
            String[] strings = login.getPermissoes().split(SEPARADOR_PERMISSOES);
            Collections.addAll(permissions, strings);
            final var token = jwtUtil.generateToken(login.getLogin(), permissions, Math.toIntExact(login.getId()));
            return new ResponseEntity<>(GetTokenResponse.builder()
                    .accessToken(token)
                    .build(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(GetTokenResponse.builder().build(), HttpStatus.UNAUTHORIZED);
        }
    }
}
