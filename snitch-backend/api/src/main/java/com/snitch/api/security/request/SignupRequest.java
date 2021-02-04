package com.snitch.api.security.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String position;
    private String username;
    private String password;
    private String email;
}
