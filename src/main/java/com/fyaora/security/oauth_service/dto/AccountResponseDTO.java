package com.fyaora.security.oauth_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountResponseDTO {
    @JsonProperty("did")
    private Long did;
    @JsonProperty("username")
    private String username;
    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("authorities")
    private List<String> authorities;
}
