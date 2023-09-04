package kz.shop.auto_parts.services;

import kz.shop.auto_parts.entities.RoleEntity;
import kz.shop.auto_parts.entities.dto.UserDto;
import kz.shop.auto_parts.entities.UserEntity;
import kz.shop.auto_parts.exceptions.UserNotFoundException;
import kz.shop.auto_parts.repositories.RoleEntityRepository;
import kz.shop.auto_parts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleEntityRepository roleEntityRepository) {
        this.userRepository = userRepository;
        this.roleEntityRepository = roleEntityRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDto createUser(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        user.setRoleEntity(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity createdUser = userRepository.save(user);

        UserDto newUserDto = new UserDto();
        newUserDto.setName(createdUser.getName());
        newUserDto.setSurname(createdUser.getSurname());
        newUserDto.setAge(createdUser.getAge());
        newUserDto.setEmail(createdUser.getEmail());
        newUserDto.setPassword(createdUser.getPassword());
        return newUserDto;
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.getUserEntityByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("Cannot find user with id - " + id, "404"));
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getByName(String name) {
        Optional<UserEntity> optionalUser = userRepository.findByNameOrderByAge(name);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return new UserDto(user.getName(), user.getSurname(), user.getAge(), user.getEmail(), user.getPassword());
        } else {
            throw new UserNotFoundException("User " + name + " not found", "404");
        }
    }

    @Override
    public UserEntity getByEmailAndPassword(String email, String password) {
        UserEntity userEntity = getByEmail(email);
        if (passwordEncoder.matches(password, userEntity.getPassword())) {
            return userEntity;
        }
        return null;
    }

    @Override
    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email).
                orElseThrow(() -> new UserNotFoundException("Cannot find user with email - " + email, "404"));
    }
}
