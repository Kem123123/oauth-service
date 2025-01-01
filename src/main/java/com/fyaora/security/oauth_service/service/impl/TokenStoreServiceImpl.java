package com.fyaora.security.oauth_service.service.impl;

import com.fyaora.security.oauth_service.dto.AuthResponseDTO;
import com.fyaora.security.oauth_service.dto.LoginDTO;
import com.fyaora.security.oauth_service.dto.RefreshKeyDTO;
import com.fyaora.security.oauth_service.model.AuthToken;
import com.fyaora.security.oauth_service.repository.AuthTokenStoreRepository;
import com.fyaora.security.oauth_service.service.TokenStoreService;
import com.fyaora.security.oauth_service.util.JwtTokenUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenStoreServiceImpl implements TokenStoreService {
    @Value("${jwt.expire.time}")
    private long expirationTime;

    private final AuthTokenStoreRepository authTokenStoreRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public TokenStoreServiceImpl(AuthTokenStoreRepository repository, JwtTokenUtil tokenUtil) {
        this.authTokenStoreRepository = repository;
        this.jwtTokenUtil = tokenUtil;
    }

    @Transactional
    public AuthResponseDTO generateTokenByUserCredentials(LoginDTO loginDTO) {
        String accessToken = jwtTokenUtil.generateAccessToken(loginDTO.getUsername());
        String refreshToken = jwtTokenUtil.generateRefreshToken(loginDTO.getUsername());

        LocalDateTime expireAt = jwtTokenUtil.getExpirationTime(accessToken);
        AuthResponseDTO responseDTO = new AuthResponseDTO(accessToken, refreshToken, expireAt);

        AuthToken token = getAuthToken(responseDTO, loginDTO.getUsername());
        authTokenStoreRepository.deleteAllByUsername(loginDTO.getUsername());
        authTokenStoreRepository.save(token);

        return responseDTO;
    }

    @Transactional
    public AuthResponseDTO generateTokenByRefreshToken(RefreshKeyDTO refreshKeyDTO) throws Exception {
        if (jwtTokenUtil.validateToken(refreshKeyDTO.getRefreshToken())) {
            String username = jwtTokenUtil.getUsernameFromToken(refreshKeyDTO.getRefreshToken());

            Optional<AuthToken> existingToken =
                    authTokenStoreRepository.findByUsernameAndRefreshToken(username, refreshKeyDTO.getRefreshToken());
            if (existingToken.isEmpty()) {
                throw new Exception("Refresh token not found or mismatched");
            }

            String accessToken = jwtTokenUtil.generateAccessToken(username);
            String refreshToken = jwtTokenUtil.generateRefreshToken(username);
            LocalDateTime expireAt = jwtTokenUtil.getExpirationTime(accessToken);
            AuthResponseDTO responseDTO = new AuthResponseDTO(accessToken, refreshToken, expireAt);

            AuthToken token = getAuthToken(responseDTO, username);
            authTokenStoreRepository.deleteAllByUsername(username);
            authTokenStoreRepository.save(token);

            return responseDTO;
        }
        return null;
    }

    public boolean validateToken(String token) {
        return jwtTokenUtil.validateToken(token);
    }

    private AuthToken getAuthToken(AuthResponseDTO responseDTO, String username) {
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken(responseDTO.getAccessToken());
        authToken.setRefreshToken(responseDTO.getRefreshToken());
        authToken.setUsername(username);
        authToken.setCreatedAt(LocalDateTime.now());
        authToken.setExpireAt(responseDTO.getExpireAt());
        return authToken;
    }
}
