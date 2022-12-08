package utility;

import entity.Customer;
import entity.Order;
import entity.Product;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class Bootstrap {

    public static List<Product> getMockProducts() {
        Product product1 = new Product();
        product1.setId(UUID.fromString("92696d28-db3a-4496-a4f0-f767deae4ec6"));
        product1.setName("tshirt");
        product1.setPrice(300d);
        product1.setCategory("Shirt");

        Product product2 = new Product();
        product2.setId(UUID.fromString("5d1a0178-448c-458b-b05e-e56e15968a90"));
        product2.setName("watch");
        product2.setPrice(400d);
        product2.setCategory("Watch");

        Product product3 = new Product();
        product3.setId(UUID.fromString("37a64052-ba48-4900-9cb9-aa7f84d57cf6"));
        product3.setName("mobile");
        product3.setPrice(3000d);
        product3.setCategory("Electronics");

        Product product4 = new Product();
        product4.setId(UUID.fromString("98c27595-55a7-45ef-8ac1-843229721d86"));
        product4.setName("book1");
        product4.setPrice(7000d); // 6300
        product4.setCategory("Books");

        Product product5 = new Product();
        product5.setId(UUID.fromString("47e67ced-ed99-4aec-892e-8fd167a93187"));
        product5.setName("book2");
        product5.setPrice(8000d); // 7200
        product5.setCategory("Books");

        return Arrays.asList(product1, product2, product3, product4, product5);
    }

    public static List<Customer> getMockCustomers() {
        return Arrays.asList(
                new Customer("e49c06c9-4cd9-4115-8a34-b73c61e91003", "chethan", 2),
                new Customer("55d280e0-2952-46ef-b3b4-f07d4d00a217","naga", 1),
                new Customer("2bdd423b-ca08-492f-9a16-996a8314adcd","gowtham", 2),
                new Customer("43592143-0667-47be-aaec-b58d33297fe6","abhi", 3)
        );
    }

    public static List<Order> getMockOrders() {
        List<Customer> customers = getMockCustomers();
        List<Product> products = getMockProducts();

        Order order1 = new Order();
        order1.setId(UUID.fromString("993c3d2a-8688-4ca0-bcf4-5214078274de"));
        order1.setDeliveryDate(LocalDate.parse("2021-02-18"));
        order1.setCustomer(customers.get(0));
        order1.setStatus("SHIPPED");
        order1.getProducts().add(products.get(0));
        order1.getProducts().add(products.get(1));

        Order order2 = new Order();
        order2.setId(UUID.fromString("f15dc79c-254b-482b-a9f0-23acb9ee48f1"));
        order2.setDeliveryDate(LocalDate.parse("2020-02-10"));
        order2.setCustomer(customers.get(1));
        order2.setStatus("DELIVERED");
        order2.getProducts().add(products.get(0));
        order2.getProducts().add(products.get(2));

        Order order3 = new Order();
        order3.setId(UUID.fromString("0250d28d-fa3b-483d-a063-bca8d67b818a"));
        order3.setDeliveryDate(LocalDate.parse("2021-03-10"));
        order3.setCustomer(customers.get(2));
        order3.setStatus("SHIPPED");
        order3.getProducts().add(products.get(1));
        order3.getProducts().add(products.get(3));

        return Arrays.asList(order1, order2, order3);
    }
}
