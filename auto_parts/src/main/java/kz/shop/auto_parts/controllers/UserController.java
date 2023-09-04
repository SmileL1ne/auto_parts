package kz.shop.auto_parts.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import kz.shop.auto_parts.entities.dto.UserDto;
import kz.shop.auto_parts.entities.UserEntity;
import kz.shop.auto_parts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save-user")
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") @Valid @Positive Long userId) {
        return new ResponseEntity<>(userService.getById(userId), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserEntity> users = userService.findAll();
        return new ResponseEntity<>(users.stream().map(user -> new UserDto(user.getName(), user.getSurname(),
                user.getAge(), user.getEmail(), user.getPassword())).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable("name") @Valid @NotBlank String name) {
        return new ResponseEntity<>(userService.getByName(name), HttpStatus.OK);
    }

    @DeleteMapping("delete-user")
    public ResponseEntity<Void> deleteUserById(@RequestParam @Valid @Positive Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
