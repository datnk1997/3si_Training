package com.hrm.mockprojecthrm.entity;

import lombok.Getter;

public enum RoleName {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USERHR("ROLE_USERHR"),
    ROLE_USER("ROLE_USER");

    public String name;

    RoleName(String name) {
        this.name = name;
    }
}
