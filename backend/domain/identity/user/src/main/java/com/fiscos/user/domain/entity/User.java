package com.fiscos.user.domain.entity;

import com.github.f4b6a3.uuid.UuidCreator;

import java.time.LocalDateTime;
import java.util.UUID;


public class User {

    private final UUID id;
    private final String name;
    private final Auth auth;
    private boolean active;
    private final LocalDateTime createdAt;

    public User(String name, Auth auth) {
        this.id = UuidCreator.getTimeBased();
        this.name = name;
        this.auth = auth;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public User(UUID id, String name, Auth auth, boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.auth = auth;
        this.active = active;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Auth getAuth() {
        return auth;
    }


    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean deactivate() {
        active = false;
        return this.isActive();
    }
}
