package repository;

import entity.Customer;
import entity.Order;
import entity.Product;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static utility.Bootstrap.getMockOrders;

public final class OrderRepository extends CrudRepository<Order> {

    private static OrderRepository instance = null;

    private OrderRepository() {
        super(getMockOrders());
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    public boolean doesCustomerHaveOrder(UUID customerId) {
        return this.findAll()
                .stream()
                .anyMatch(order -> order.getCustomer().getId().equals(customerId));
    }

    public boolean doesCustomerHaveOrder(String customerId) {
        UUID cId = UUID.fromString(customerId);
        return doesCustomerHaveOrder(cId);
    }

    public double getPriceSumOfOrder(UUID orderId) {
        return this.findAll()
                .stream()
                .filter(order -> order.getId().equals(orderId))
                .map(Order::getProducts)
                .flatMap(Set::stream)
                .mapToDouble(Product::getPrice)
                .sum();
    }


    public Order getOrderWithMaximumPurchase() {
        Order firstSolution = this.findAll()
                .stream()
                .max((orderA, orderB) -> {
                    Double priceA = getPriceSumOfOrder(orderA.getId());
                    Double priceB = getPriceSumOfOrder(orderB.getId());
                    return priceA.compareTo(priceB);
                }).orElse(null);

        Order secondSolution = this.findAll()
                .stream()
                .max(Comparator.comparingDouble(Order::getTotalPrice))
                .orElse(null);

        return secondSolution;
    }

    public boolean hasProductBeenPurchased(Product product) {
        return this.findAll()
                .stream()
                .anyMatch(order -> order.getProducts().contains(product));
    }

    public List<Order> getOrdersWithBabyProduct() {
        return this.findAll()
                .stream()
                .filter(order -> order.getProducts()
                        .stream()
                        .map(Product::getCategory)
                        .anyMatch(category -> category.equals("Baby"))
                )
                .collect(toList());
    }

    public Set<Product> getProductsWithTier2Date() {
        return this.findAll()
                .stream()
                .filter(order -> order.getCustomer().getTier().equals(2))
                .filter(order -> order.getDeliveryDate().isAfter(LocalDate.parse("2021-02-01")))
                .filter(order -> order.getDeliveryDate().isBefore(LocalDate.of(2021, 4, 1)))
                .map(Order::getProducts)
                .flatMap(Set::stream)
                .collect(toSet());
    }

    public List<Order> getThreeMostRecentOrders() {
        return this.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(toList());
    }

    public List<Product> getOnDate() {
        return this.findAll()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .peek(System.out::println)
                .map(Order::getProducts)
                .flatMap(Set::stream)
                .distinct()
                .collect(toList());
    }

    public Double getLumpSumInYear(int year, int month) {
        return this.findAll()
                .stream()
                .filter(order -> order.getOrderDate().getYear() == year)
                .filter(order -> order.getOrderDate().getMonth().equals(Month.of(month)))
                .map(Order::getProducts)
                .flatMap(Set::stream)
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Map<Month, Double> getAllMonthsLumpSumOfYear(int year) {
        return this.findAll()
                .stream()
                .filter(order -> order.getOrderDate().getYear() == year)
                .collect(
                        groupingBy(
                                order -> order.getOrderDate().getMonth(),
                                summingDouble(Order::getTotalPrice)
                        )
                );
    }

    public Double orderAverageOnDate(LocalDate date) {
        return this.findAll()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(date))
                .map(Order::getProducts)
                .flatMap(Set::stream)
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    public Map<UUID, Integer> getMapOrderIdAndProductCount() {
        return this.findAll()
                .stream()
                .collect(
                        toMap(
                                Order::getId,
                                Order::getTotalProducts
                        )
                );
    }

    public Map<Customer, List<Order>> getOrdersGroupedByCustomers() {
        return this.findAll()
                .stream()
                .collect(groupingBy(Order::getCustomer));
    }

    public Map<Order, Double> getOrderMapWithTotalSum() {
        return this.findAll()
                .stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                Order::getTotalPrice
                        )
                );
    }

}
