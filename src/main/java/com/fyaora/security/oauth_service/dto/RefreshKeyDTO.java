package com.fyaora.security.oauth_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RefreshKeyDTO implements Serializable {
    @JsonProperty("refreshToken")
    private String refreshToken;

    public RefreshKeyDTO() {}

    public RefreshKeyDTO(String token) {
        this.refreshToken = token;
    }
}
