package com.hcl.training.generic;

import java.util.Collection;

public final class GenericToolkit {
    private GenericToolkit() { }

    public static <T extends Comparable<T>> T max(T first, T second) {
        return first.compareTo(second) >= 0 ? first : second;
    }

    public static <T> void copyAll(Collection<? super T> destination,
                                   Collection<? extends T> source) {
        destination.addAll(source);
    }
}
