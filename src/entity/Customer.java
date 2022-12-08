package entity;

import java.util.Objects;
import java.util.UUID;

public class Customer extends BaseEntity {

    private String name;

    private Integer tier;

    public Customer() {
        super();
    }

    public Customer(String id, String name, Integer tier) {
        super(UUID.fromString(id));
        this.name = name;
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(this.getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}