package com.minsait.api.controller.dto;

import lombok.Data;

@Data
public class ClienteResponse {
    Long id;
    String nome;
    String endereco;
    String telefone;
    String email;
}
