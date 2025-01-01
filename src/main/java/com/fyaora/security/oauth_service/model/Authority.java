package com.fyaora.security.oauth_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long did;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_did", nullable = false)
    private UserInfo user;

    @Column(name = "authority", nullable = false)
    private String authority;

    public Long getDid() { return did; }

    public void setDid(Long did) { this.did = did; }

    public UserInfo getUser() { return user; }

    public void setUser(UserInfo user) { this.user = user; }

    public String getAuthority() { return authority; }

    public void setAuthority(String authority) { this.authority = authority; }
}
