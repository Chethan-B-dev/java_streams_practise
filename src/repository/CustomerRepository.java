package repository;

import entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

import static utility.Bootstrap.getMockCustomers;

public class CustomerRepository extends CrudRepository<Customer> {

    private final OrderRepository orderRepository = new OrderRepository();

    public CustomerRepository() {
        super(getMockCustomers());
    }

    public List<Customer> findCustomersWhoHaveNotPlacedAnOrder() {
        return this.findAll()
                .stream()
                .filter(customer -> !orderRepository.doesCustomerHaveOrder(customer.getId()))
                .collect(Collectors.toList());
    }
}
