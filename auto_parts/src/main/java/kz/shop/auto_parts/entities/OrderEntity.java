package kz.shop.auto_parts.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false, columnDefinition = "NUMERIC(8,2)")
    private Float totalPrice;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String shippingAddress;
    @Column(nullable = false)
    private String orderStatus;

    @Column(nullable = false, updatable = false, name = "created-at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "productId")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "userId")
    private UserEntity user;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
