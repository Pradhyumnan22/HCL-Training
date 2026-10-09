package com.hcl.training.entity;

public final class Traveller extends User {
    public Traveller(long id, String name) { super(id, name); }
    @Override public String getRole() { return "TRAVELLER"; }
}
