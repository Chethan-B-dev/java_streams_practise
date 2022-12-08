package entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Order extends BaseEntity{
    private LocalDate orderDate = LocalDate.now();
    private LocalDate deliveryDate;
    private String status;
    private Customer customer;
    private Set<Product> products = new HashSet<>();

    public Order() {
        super();
    }

    public Order(String id, LocalDate deliveryDate, String status, Customer customer) {
        super(UUID.fromString(id));
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(this.getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + this.getId() +
                "status=" + status +
                "orderDate=" + orderDate +
                '}';
    }
}

