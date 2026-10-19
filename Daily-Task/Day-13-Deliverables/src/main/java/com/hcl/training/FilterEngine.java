package com.hcl.training;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class FilterEngine {
    private FilterEngine() { }

    public static List<Product> filter(List<Product> products, Predicate<Product> rule) {
        return products.stream().filter(rule).toList();
    }

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Backpack", "Travel", 2400, true),
                new Product("Tent", "Travel", 7800, false),
                new Product("Headphones", "Electronics", 3200, true),
                new Product("Water Bottle", "Travel", 900, true));

        Predicate<Product> affordable = product -> product.price() <= 5000;
        Predicate<Product> travelCategory = product -> product.category().equalsIgnoreCase("Travel");
        Predicate<Product> available = Product::inStock;
        Predicate<Product> travelAndAvailable = travelCategory.and(available);
        System.out.println("Composed filter: " + filter(products, affordable.and(travelAndAvailable)));

        Function<Product, String> normalizeName = Product::name;
        Function<String, String> label = String::toUpperCase;
        System.out.println("Function andThen: " + normalizeName.andThen(label).apply(products.get(0)));
        System.out.println("Function compose: " + label.compose(normalizeName).apply(products.get(1)));

        // Four method-reference forms: static, bound instance, unbound instance, constructor.
        Consumer<Product> printer = FilterEngine::printProduct;            // static
        Consumer<Product> boundPrinter = new Printer()::print;              // bound instance
        Function<Product, String> unboundName = Product::name;              // unbound instance
        Supplier<Printer> factory = Printer::new;                            // constructor
        printer.accept(products.get(0));
        boundPrinter.accept(products.get(1));
        System.out.println("Unbound reference: " + unboundName.apply(products.get(2)));
        System.out.println("Constructor reference: " + factory.get());

        Supplier<String> defaultMessage = () -> "No matching products";
        System.out.println("Supplier default: " + (filter(products, product -> product.price() > 10_000).stream()
                .findFirst().map(Product::name).orElseGet(defaultMessage)));
    }

    private static void printProduct(Product product) {
        System.out.println("Product: " + product.name() + " ₹" + product.price());
    }

    private static final class Printer {
        void print(Product product) { System.out.println("Bound printer: " + product.name()); }
        @Override public String toString() { return "Printer"; }
    }
}
