package com.fyaora.security.oauth_service.repository;

import com.fyaora.security.oauth_service.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsernameAndEnabled(String username, Boolean status);
    Optional<UserInfo> findByUsername(String username);
    Optional<UserInfo> findByDidAndUsername(Long did, String username);
}
