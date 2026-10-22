package com.pedro.loveall.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column (nullable = false, length = 254)
    private String email;

    @Column (nullable = false, length = 255)
    private String passwordHash;

    @Column (nullable = false, length = 100)
    private String name;

    @CreationTimestamp
    @Column (nullable = false, updatable = false)
    private Instant createdAt;

    public User(String email, String passwordHash, String name) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
    }
}
