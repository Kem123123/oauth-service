package com.fyaora.security.oauth_service.service.impl;

import com.fyaora.security.oauth_service.dto.AccountDTO;
import com.fyaora.security.oauth_service.dto.AccountResponseDTO;
import com.fyaora.security.oauth_service.dto.AccountStatusDTO;
import com.fyaora.security.oauth_service.model.Authority;
import com.fyaora.security.oauth_service.model.UserInfo;
import com.fyaora.security.oauth_service.repository.UserInfoRepository;
import com.fyaora.security.oauth_service.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addAccount(AccountDTO accountDTO) throws Exception {
        Optional<UserInfo> dbUser = userInfoRepository.findByUsername(accountDTO.getUsername());
        if (dbUser.isPresent()) {
            throw new Exception("User already exist");
        }
        UserInfo info = getUserInfo(accountDTO);
        userInfoRepository.save(info);
    }

    public AccountResponseDTO accountStatusChange(AccountStatusDTO statusDTO) throws Exception {
        Optional<UserInfo> info = userInfoRepository.findByDidAndUsername(statusDTO.getDid(), statusDTO.getUsername());
        if (info.isEmpty()) {
            throw new Exception("Account does not exist");

        } else {
            UserInfo userInfo = info.get();
            userInfo.setEnabled(statusDTO.getStatus());
            userInfoRepository.save(userInfo);

            return getAccountResponse(userInfo);
        }
    }

    private UserInfo getUserInfo(AccountDTO accountDTO) {
        UserInfo info = new UserInfo();
        info.setUsername(accountDTO.getUsername());
        info.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        info.setEnabled(Boolean.TRUE);

        List<Authority> authorities = new ArrayList<>();
        accountDTO.getAuthorities().forEach(auth -> {
            Authority authority = new Authority();
            authority.setAuthority(auth);
            authority.setUser(info);
            authorities.add(authority);
        });
        info.setAuthorities(authorities);

        return info;
    }

    private AccountResponseDTO getAccountResponse(UserInfo info) {
        AccountResponseDTO responseDTO = new AccountResponseDTO();
        responseDTO.setDid(info.getDid());
        responseDTO.setUsername(info.getUsername());
        responseDTO.setStatus(info.getEnabled());

        List<String> authorities = new ArrayList<>();
        info.getAuthorities().forEach(auth -> authorities.add(auth.getAuthority()));

        responseDTO.setAuthorities(authorities);
        return responseDTO;
    }
}
