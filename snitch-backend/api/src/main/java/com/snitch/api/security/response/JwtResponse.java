package com.snitch.api.security.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private Long userId;
    private String username;
    private String userEmail;
    private List<String> userRoles;
}
