package com.fyaora.security.oauth_service.service;

import com.fyaora.security.oauth_service.dto.AccountDTO;
import com.fyaora.security.oauth_service.dto.AccountResponseDTO;
import com.fyaora.security.oauth_service.dto.AccountStatusDTO;

public interface AccountService {

    void addAccount(AccountDTO accountDTO) throws Exception;
    AccountResponseDTO accountStatusChange(AccountStatusDTO statusDTO) throws Exception;
}
