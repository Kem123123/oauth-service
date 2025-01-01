package com.fyaora.security.oauth_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountStatusDTO {
    @JsonProperty("did")
    private Long did;
    @JsonProperty("username")
    private String username;
    @JsonProperty("status")
    private Boolean status;
}
