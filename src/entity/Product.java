package entity;

import java.util.Objects;
import java.util.UUID;

public class Product extends BaseEntity {
    private String name;
    private String category;
    private Double price;

    public Product() {
        super();
    }

    public Product(String id, String name, String category, Double price) {
        super(UUID.fromString(id));
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product withPrice(double newPrice) {
        this.price = newPrice;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(this.getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                "price='" + price + '\'' +
                '}';
    }
}
