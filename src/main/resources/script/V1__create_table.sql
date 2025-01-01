-- Table for storing access and refresh tokens
CREATE TABLE oauth_token (
    did BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    access_token VARCHAR(1024),
    refresh_token VARCHAR(1024),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expire_at TIMESTAMP,
    INDEX idx_access_token (access_token(255)),
    INDEX idx_refresh_token (refresh_token(255))
);

-- Table for storing user details
CREATE TABLE users (
    did BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Table for storing user authorities/roles
CREATE TABLE authorities (
    did BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_did BIGINT NOT NULL,
    authority VARCHAR(255) NOT NULL,
    CONSTRAINT fk_user_did FOREIGN KEY (user_did) REFERENCES users(did)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);