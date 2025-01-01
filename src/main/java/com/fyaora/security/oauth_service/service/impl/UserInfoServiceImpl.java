package com.fyaora.security.oauth_service.service.impl;

import com.fyaora.security.oauth_service.model.UserInfo;
import com.fyaora.security.oauth_service.repository.UserInfoRepository;
import com.fyaora.security.oauth_service.service.UserInfoService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo getUserInfoByUsername(String username) throws UsernameNotFoundException {
        return userInfoRepository.findByUsernameAndEnabled(username, Boolean.TRUE)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
