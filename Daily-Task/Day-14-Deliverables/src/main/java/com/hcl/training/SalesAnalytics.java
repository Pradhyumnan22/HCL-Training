package com.hcl.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SalesAnalytics {
    private SalesAnalytics() { }

    record Order(int id, String customer, String category, String status,
                 double amount, int basketSize, List<String> productCodes) { }

    private static final Map<String, String> PRODUCT_NAMES = Map.of(
            "P1", "Ooty Escape", "P2", "Goa Beach", "P3", "Mysore Heritage",
            "P4", "Chennai Culture", "P5", "Coorg Hills");

    public static void main(String[] args) {
        List<Order> orders = createOrders(1_000);
        System.out.println("Orders analyzed: " + orders.size());
        System.out.println("Revenue by category: " + revenueByCategory(orders));
        System.out.println("Top 3 customers: " + topCustomers(orders));
        System.out.println("Orders by status: " + ordersByStatus(orders).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size())));
        System.out.printf("Average basket size: %.2f%n", averageBasketSize(orders));
        System.out.println("Joined products: " + joinedProductList(orders.stream().limit(3).toList()));
        compareSequentialAndParallel(orders);
    }

    private static List<Order> createOrders(int count) {
        String[] customers = {"Asha", "Ravi", "Meera", "Kiran", "Nila", "Dev", "Ishaan"};
        String[] categories = {"Adventure", "Beach", "Heritage", "Hill Station"};
        String[] statuses = {"PAID", "PAID", "PAID", "PENDING", "CANCELLED"};
        return IntStream.rangeClosed(1, count).mapToObj(id -> {
            String customer = customers[id % customers.length];
            String category = categories[id % categories.length];
            String status = statuses[id % statuses.length];
            double amount = 1500 + (id % 12) * 475.0;
            int basket = 1 + id % 5;
            List<String> products = List.of("P" + (1 + id % 5), "P" + (1 + (id + 1) % 5));
            return new Order(id, customer, category, status, amount, basket, products);
        }).toList();
    }

    public static Map<String, Double> revenueByCategory(List<Order> orders) {
        return orders.stream().collect(Collectors.groupingBy(Order::category, Collectors.summingDouble(Order::amount)));
    }

    public static Map<String, Double> topCustomers(List<Order> orders) {
        return orders.stream().collect(Collectors.groupingBy(Order::customer, Collectors.summingDouble(Order::amount)))
                .entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (first, second) -> first, java.util.LinkedHashMap::new));
    }

    public static Map<Boolean, List<Order>> ordersByStatus(List<Order> orders) {
        return orders.stream().collect(Collectors.partitioningBy(order -> "PAID".equals(order.status())));
    }

    public static double averageBasketSize(List<Order> orders) {
        return orders.stream().mapToInt(Order::basketSize).average().orElse(0);
    }

    public static List<String> joinedProductList(List<Order> orders) {
        return orders.stream().flatMap(order -> order.productCodes().stream())
                .distinct().map(PRODUCT_NAMES::get).toList();
    }

    private static void compareSequentialAndParallel(List<Order> orders) {
        ToDoubleFunction<Order> cpuHeavyCalculation = order -> {
            double value = order.amount();
            for (int i = 0; i < 2_000; i++) value = Math.sqrt(value * value + i + order.id());
            return value;
        };
        long sequentialStart = System.nanoTime();
        double sequentialTotal = orders.stream().mapToDouble(cpuHeavyCalculation).sum();
        long sequentialNanos = System.nanoTime() - sequentialStart;
        long parallelStart = System.nanoTime();
        double parallelTotal = orders.parallelStream().mapToDouble(cpuHeavyCalculation).sum();
        long parallelNanos = System.nanoTime() - parallelStart;
        System.out.println("CPU-heavy sequential total=" + sequentialTotal + ", ns=" + sequentialNanos);
        System.out.println("CPU-heavy parallel total=" + parallelTotal + ", ns=" + parallelNanos);
    }
}
