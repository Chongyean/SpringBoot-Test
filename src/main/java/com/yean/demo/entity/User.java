package com.yean.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private Integer age;
    private String address;
    private String role;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

     @PrePersist
    public void prePersist() {
         LocalDateTime now = LocalDateTime.now();
         this.createdAt = now;
         this.updatedAt = now;
     }

}
