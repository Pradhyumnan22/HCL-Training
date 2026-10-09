package com.hcl.training.entity;

public abstract class User extends BaseEntity {
    private final String name;

    protected User(long id, String name) {
        super(id);
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name is required");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getRole();
}
