package kz.shop.auto_parts.controllers.securityControllers;

import jakarta.validation.Valid;
import kz.shop.auto_parts.config.jwt.JwtProvider;
import kz.shop.auto_parts.entities.UserEntity;
import kz.shop.auto_parts.entities.dto.UserDto;
import kz.shop.auto_parts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserDto userDto = new UserDto();
        userDto.setPassword(registrationRequest.getPassword());
        userDto.setEmail(registrationRequest.getEmail());
        userDto.setSurname(registrationRequest.getSurname());
        userDto.setName(registrationRequest.getName());
        userDto.setAge(registrationRequest.getAge());
        userService.createUser(userDto);
        return "OK";
    }

    @PostMapping("/authenticate")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.getByEmailAndPassword(request.getEmail(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getEmail());
        return new AuthResponse(token);
    }
}
