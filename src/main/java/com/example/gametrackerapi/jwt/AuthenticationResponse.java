package com.example.gametrackerapi.jwt;

import java.io.Serializable;

public record AuthenticationResponse(String token) implements Serializable {
}
