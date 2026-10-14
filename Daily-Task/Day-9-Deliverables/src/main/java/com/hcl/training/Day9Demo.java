package com.hcl.training;

import com.hcl.training.generic.Box;
import com.hcl.training.generic.GenericToolkit;
import com.hcl.training.generic.Pair;
import com.hcl.training.model.Product;
import com.hcl.training.repository.InMemoryRepository;
import com.hcl.training.resource.AuditResource;
import com.hcl.training.service.CsvSeedLoader;

import java.util.ArrayList;
import java.util.List;

public final class Day9Demo {
    private Day9Demo() { }

    public static void main(String[] args) {
        Pair<String, Integer> packageSeats = new Pair<>("OOTY-01", 12);
        Box<Integer> seatPrice = new Box<>(4500);
        System.out.println("Pair: " + packageSeats + ", Box value: " + seatPrice.asDouble());
        System.out.println("Bounded max: " + GenericToolkit.max(4500, 3000));

        List<Integer> source = List.of(2, 3, 4);
        List<Number> destination = new ArrayList<>();
        GenericToolkit.copyAll(destination, source);
        System.out.println("PECS copy: " + destination);

        var repository = new InMemoryRepository<Product, Long>(Product::id);
        CsvSeedLoader.loadProducts(repository);
        System.out.println("Seeded products: " + repository.findAll());
        System.out.println("Product 2: " + repository.findById(2L).orElseThrow());

        try (var outer = new AuditResource("outer resource");
             var inner = new AuditResource("inner resource")) {
            System.out.println("try-with-resources body executed");
        }
    }
}
