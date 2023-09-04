package kz.shop.auto_parts.repositories;

import kz.shop.auto_parts.entities.OrderEntity;
import kz.shop.auto_parts.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<List<OrderEntity>> findByOrderStatus(String orderStatus);

    Optional<List<OrderEntity>> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<OrderEntity> deleteByOrderId(Long id);

    List<OrderEntity> findAllByUserUserId(Long userId);

    Optional<OrderEntity> findByOrderId(Long orderId);
}
