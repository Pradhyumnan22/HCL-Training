package com.hcl.training.service;

import com.hcl.training.model.Product;
import com.hcl.training.repository.CrudRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public final class CsvSeedLoader {
    private CsvSeedLoader() { }

    public static void loadProducts(CrudRepository<Product, Long> repository) {
        var resource = CsvSeedLoader.class.getResourceAsStream("/products.csv");
        if (resource == null) {
            throw new IllegalStateException("products.csv was not found on the classpath");
        }
        try (var input = resource;
             var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            reader.lines().skip(1).forEach(line -> {
                String[] columns = line.split(",");
                repository.save(new Product(Long.parseLong(columns[0]), columns[1],
                        Double.parseDouble(columns[2])));
            });
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to read products.csv", e);
        }
    }
}
