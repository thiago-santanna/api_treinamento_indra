package com.minsait.api.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MessageResponse {
    private LocalDateTime date;
    private String message;
    private Boolean error;
}
