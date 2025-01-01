package com.fyaora.security.oauth_service.service.impl;

import com.fyaora.security.oauth_service.model.UserInfo;
import com.fyaora.security.oauth_service.service.UserInfoService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoService userInfoService;

    public UserDetailsServiceImpl(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo info = userInfoService.getUserInfoByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        info.getAuthorities().forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth.getAuthority())));
        return new User(info.getUsername(), info.getPassword(), authorities);
    }
}
