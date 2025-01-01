package com.fyaora.security.oauth_service.repository;

import com.fyaora.security.oauth_service.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthTokenStoreRepository extends JpaRepository<AuthToken, Long> {
    void deleteAllByUsername(String username);
    Optional<AuthToken> findByUsernameAndRefreshToken(String username, String refreshToken);

}
