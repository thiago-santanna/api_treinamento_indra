package com.minsait.api.controller.dto;

import lombok.Data;

@Data
public class GetTokenRequest {
    private String userName;
    private String password;
}
