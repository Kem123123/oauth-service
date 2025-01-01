package com.fyaora.security.oauth_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "did")
    private Long did;

    @Column(name = "username", length = 255, nullable = false )
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Authority> authorities;

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() { return authorities; }

    public void setAuthorities(List<Authority> authorities) { this.authorities = authorities; }
}
