package com.fola.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationRequest {
    private final String username;
    private final String password;
}
