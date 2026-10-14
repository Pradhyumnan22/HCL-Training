package com.hcl.training.resource;

public final class AuditResource implements AutoCloseable {
    private final String name;

    public AuditResource(String name) {
        this.name = name;
        System.out.println("open: " + name);
    }

    @Override
    public void close() { System.out.println("close: " + name); }
}
