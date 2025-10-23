package com.fiscos.user.domain.valueobject;

public enum Role {
    ADMIN,
    CUSTOMER,
    USER;

    public static Role fromString(String value) {
        if (value == null) {
            return defaultRole();
        }
        try {
            return Role.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return defaultRole();
        }
    }

    public static Role defaultRole() {
        return USER;
    }
}