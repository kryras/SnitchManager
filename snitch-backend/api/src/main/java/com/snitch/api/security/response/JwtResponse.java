package com.snitch.api.security.response;

import java.util.List;

public class JwtResponse {

    private String token;
    private Long userId;
    private String username;
    private String userEmail;
    private List<String> userRoles;

    public JwtResponse(String token, Long userId, String username, String userEmail, List<String> userRoles) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.userRoles = userRoles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }
}
