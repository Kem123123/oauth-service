package com.fyaora.security.oauth_service.controller;

import com.fyaora.security.oauth_service.dto.LoginDTO;
import com.fyaora.security.oauth_service.dto.RefreshKeyDTO;
import com.fyaora.security.oauth_service.dto.AuthResponseDTO;
import com.fyaora.security.oauth_service.service.TokenStoreService;
import com.fyaora.security.oauth_service.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenStoreService tokenStoreService;

    public AuthController(AuthenticationManager authenticationManager, TokenStoreService storeService) {
        this.authenticationManager = authenticationManager;
        this.tokenStoreService = storeService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO loginDTO) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        AuthResponseDTO responseDTO = tokenStoreService.generateTokenByUserCredentials(loginDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> generateNewAccessKey(@RequestBody RefreshKeyDTO refreshKeyDTO) throws Exception {
        AuthResponseDTO responseDTO = tokenStoreService.generateTokenByRefreshToken(refreshKeyDTO);
        if (null != responseDTO) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
        }
    }
}
