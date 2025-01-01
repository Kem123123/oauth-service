package com.fyaora.security.oauth_service.service;

import com.fyaora.security.oauth_service.dto.AuthResponseDTO;
import com.fyaora.security.oauth_service.dto.LoginDTO;
import com.fyaora.security.oauth_service.dto.RefreshKeyDTO;

import java.io.IOException;

public interface TokenStoreService {
    AuthResponseDTO generateTokenByUserCredentials(LoginDTO loginDTO) throws Exception;
    AuthResponseDTO generateTokenByRefreshToken(RefreshKeyDTO refreshKeyDTO) throws Exception;
    boolean validateToken(String token) throws IOException;
}
