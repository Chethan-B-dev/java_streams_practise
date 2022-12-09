package repository;

import entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

import static utility.Bootstrap.getMockCustomers;

public final class CustomerRepository extends CrudRepository<Customer> {

    private static CustomerRepository instance = null;
    private final OrderRepository orderRepository = OrderRepository.getInstance();

    private CustomerRepository() {
        super(getMockCustomers());
    }

    public static CustomerRepository getInstance()
    {
        if (instance == null){
            instance = new CustomerRepository();
        }
        return instance;
    }

    public List<Customer> findCustomersWhoHaveNotPlacedAnOrder() {
        return this.findAll()
                .stream()
                .filter(customer -> !orderRepository.doesCustomerHaveOrder(customer.getId()))
                .collect(Collectors.toList());
    }
}
