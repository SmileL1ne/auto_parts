package kz.shop.auto_parts.repositories;

import kz.shop.auto_parts.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> getUserEntityByUserId(Long id);

    Optional<UserEntity> findByNameOrderByAge(String name);

    Optional<UserEntity> findByEmail(String email);

}
