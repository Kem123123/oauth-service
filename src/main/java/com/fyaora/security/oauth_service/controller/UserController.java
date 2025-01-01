package com.fyaora.security.oauth_service.controller;

import com.fyaora.security.oauth_service.dto.AccountDTO;
import com.fyaora.security.oauth_service.dto.AccountResponseDTO;
import com.fyaora.security.oauth_service.dto.AccountStatusDTO;
import com.fyaora.security.oauth_service.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class UserController {

    private final AccountService accountService;

    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        accountService.addAccount(accountDTO);
        return ResponseEntity.ok("User account is created");
    }

    @PostMapping("/status")
    public ResponseEntity<?> changeAccountStatus(@RequestBody AccountStatusDTO accountStatusDTO) throws Exception {
        AccountResponseDTO responseDTO = accountService.accountStatusChange(accountStatusDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
