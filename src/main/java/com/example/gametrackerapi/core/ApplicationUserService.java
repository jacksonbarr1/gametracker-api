package com.example.gametrackerapi.core;

import java.util.UUID;

public interface ApplicationUserService {

    UUID createUser(CreateUserRequest request);
}
