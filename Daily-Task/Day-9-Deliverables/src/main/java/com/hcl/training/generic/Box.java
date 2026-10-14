package com.hcl.training.generic;

public final class Box<T extends Number> {
    private final T value;

    public Box(T value) {
        this.value = value;
    }

    public T get() { return value; }
    public double asDouble() { return value.doubleValue(); }
}
