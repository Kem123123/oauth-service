package com.fyaora.security.oauth_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AuthResponseDTO {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expireAt;

    public AuthResponseDTO(String accessToken, String refreshToken, LocalDateTime expireAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireAt = expireAt;
    }

}
