package com.minsait.api.controller.dto;

import lombok.Data;

@Data
public class UsuarioRequest {
    Long id;
    String nome;
    String login;
    String senha;
    String permissoes;
    String email;
}
