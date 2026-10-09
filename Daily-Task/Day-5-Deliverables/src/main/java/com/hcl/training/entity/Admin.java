package com.hcl.training.entity;

public final class Admin extends User {
    public Admin(long id, String name) { super(id, name); }
    @Override public String getRole() { return "ADMIN"; }
}
