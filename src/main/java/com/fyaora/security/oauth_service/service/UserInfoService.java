package com.fyaora.security.oauth_service.service;

import com.fyaora.security.oauth_service.model.UserInfo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserInfoService {
    UserInfo getUserInfoByUsername(String username) throws UsernameNotFoundException;
}
