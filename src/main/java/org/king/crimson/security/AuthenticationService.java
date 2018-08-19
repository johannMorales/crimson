package org.king.crimson.security;

import org.king.crimson.model.security.User;

public interface AuthenticationService {
    
    User register(SignUpRequest request);
}
