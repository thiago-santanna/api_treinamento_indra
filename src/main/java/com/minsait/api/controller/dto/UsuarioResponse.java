package com.minsait.api.controller.dto;

import lombok.Data;

@Data
public class UsuarioResponse {
    Long id;
    String nome;
    String login;
    String email;
    String permissoes;
}
