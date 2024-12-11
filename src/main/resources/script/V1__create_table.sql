-- Table for storing client details
CREATE TABLE oauth_client_details (
    client_id VARCHAR(255) PRIMARY KEY,
    client_secret VARCHAR(255) NOT NULL,
    scope VARCHAR(255) NOT NULL,
    authorized_grant_types VARCHAR(255) NOT NULL,
    web_server_redirect_uri VARCHAR(255),
    authorities VARCHAR(255),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information TEXT,
    autoapprove VARCHAR(255)
);

-- Table for storing access tokens
CREATE TABLE oauth_access_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    client_id VARCHAR(255),
    authentication BLOB,
    refresh_token VARCHAR(255)
);

-- Table for storing refresh tokens
CREATE TABLE oauth_refresh_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication BLOB
);

-- Table for storing user details
CREATE TABLE users (
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Table for storing user authorities/roles
CREATE TABLE authorities (
    username VARCHAR(255),
    authority VARCHAR(255),
    FOREIGN KEY (username) REFERENCES users(username)
);
