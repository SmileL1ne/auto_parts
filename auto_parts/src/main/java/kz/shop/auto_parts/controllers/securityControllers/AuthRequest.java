package kz.shop.auto_parts.controllers.securityControllers;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
    private String name;
    private String surname;
    private Integer age;
}
