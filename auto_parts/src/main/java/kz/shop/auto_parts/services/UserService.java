package kz.shop.auto_parts.services;

import kz.shop.auto_parts.entities.dto.UserDto;
import kz.shop.auto_parts.entities.UserEntity;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserEntity getById(Long id);

    List<UserEntity> findAll();

    void deleteUserById(Long id);

    UserDto getByName(String name);

    UserEntity getByEmailAndPassword(String email, String password);

    UserEntity getByEmail(String email);
}
