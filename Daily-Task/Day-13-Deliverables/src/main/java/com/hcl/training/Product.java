package com.hcl.training;

public record Product(String name, String category, double price, boolean inStock) {
    public Product {
        if (name == null || category == null || price < 0) throw new IllegalArgumentException("Invalid product");
    }
}
