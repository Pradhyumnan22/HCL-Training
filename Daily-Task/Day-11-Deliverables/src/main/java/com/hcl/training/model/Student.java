package com.hcl.training.model;

import java.util.Objects;

public final class Student {
    private final long id;
    private final String name;
    private final String email;

    public Student(long id, String name, String email) {
        if (id <= 0 || name == null || name.isBlank() || email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Valid id, name, and email are required");
        }
        this.id = id; this.name = name; this.email = email;
    }
    public long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    @Override public boolean equals(Object other) { return other instanceof Student s && id == s.id; }
    @Override public int hashCode() { return Objects.hash(id); }
    @Override public String toString() { return "Student{id=" + id + ", name='" + name + "', email='" + email + "'}"; }
}
