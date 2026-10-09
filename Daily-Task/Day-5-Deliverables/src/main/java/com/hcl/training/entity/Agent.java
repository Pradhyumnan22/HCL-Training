package com.hcl.training.entity;

public final class Agent extends User {
    public Agent(long id, String name) { super(id, name); }
    @Override public String getRole() { return "AGENT"; }
}
