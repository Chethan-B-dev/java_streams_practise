package repository;

import entity.Customer;
import entity.Order;
import entity.Product;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.toMap;
import static utility.Bootstrap.getMockOrders;

public class OrderRepository extends CrudRepository<Order> {

    public OrderRepository() {
        super(getMockOrders());
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
        return this.findAll()
                .stream()
                .max((orderA, orderB) -> {
                    Double priceA = getPriceSumOfOrder(orderA.getId());
                    Double priceB = getPriceSumOfOrder(orderB.getId());
                    return priceA.compareTo(priceB);
                }).orElse(null);
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
                .collect(Collectors.toList());
    }

    public Set<Product> getProductsWithTier2Date() {
        return this.findAll()
                .stream()
                .filter(order -> order.getCustomer().getTier().equals(2))
                .filter(order -> order.getDeliveryDate().isAfter(LocalDate.parse("2021-02-01")))
                .filter(order -> order.getDeliveryDate().isBefore(LocalDate.parse("2021-04-01")))
                .map(Order::getProducts)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public List<Order> getThreeMostRecentOrders() {
        return this.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Product> getOnDate() {
        return this.findAll()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .peek(System.out::println)
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
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
                .collect(groupingBy(order -> order.getOrderDate().getMonth(),
                        summingDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum())
                ));
    }

    public Double orderAverageOnDate(LocalDate date) {
        return this.findAll()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(date))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    public Map<UUID, Integer> getMapOrderIdAndProductCount() {
        return this.findAll()
                .stream()
                .collect(toMap(Order::getId, order -> order.getProducts().size()));
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
                                order -> order
                                        .getProducts()
                                        .stream()
                                        .mapToDouble(Product::getPrice)
                                        .sum()
                        )
                );
    }

}
