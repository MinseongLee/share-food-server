package com.caring.food.modules.common;


import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = "id")
@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void createdAt() {
        createdAt = LocalDateTime.now();
    }
}
