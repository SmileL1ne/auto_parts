package kz.shop.auto_parts.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false, columnDefinition = "NUMERIC(8,2) DEFAULT 0")
    private Float price;
    @Column(nullable = false, columnDefinition = "TEXT DEFAULT 'The best product ever!'")
    private String description;
    @Column(nullable = false, updatable = false, name = "created-at")
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
