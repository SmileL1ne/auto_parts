package kz.shop.auto_parts.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'dummy@email.com'", unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, name = "created-at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    private RoleEntity roleEntity;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
