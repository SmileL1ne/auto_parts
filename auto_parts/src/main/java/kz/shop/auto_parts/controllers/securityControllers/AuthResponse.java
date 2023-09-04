package kz.shop.auto_parts.controllers.securityControllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}

