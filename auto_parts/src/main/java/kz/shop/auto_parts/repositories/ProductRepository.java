package kz.shop.auto_parts.repositories;

import kz.shop.auto_parts.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAll();

    Optional<ProductEntity> getProductEntityByProductId(Long id);

    List<ProductEntity> getAllByAmountGreaterThan(Integer amount);

    List<ProductEntity> getAllByNameStartingWithOrderByAmountDesc(String startChars);

    List<ProductEntity> findByDescriptionContainingIgnoreCase(String keyword);
}
