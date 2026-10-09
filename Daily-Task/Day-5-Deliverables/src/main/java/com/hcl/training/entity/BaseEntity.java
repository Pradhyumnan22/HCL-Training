package com.hcl.training.entity;

public abstract class BaseEntity {
    private final long id;

    protected BaseEntity(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Entity id must be greater than zero");
        }
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
