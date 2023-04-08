package com.minsait.api.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTokenResponse {
    private String accessToken;
}
