package com.example.gametrackerapi.jwt;

import java.io.Serializable;

public record AuthenticationRequest(String email, String password) implements Serializable {
}
